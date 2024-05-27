package com.br.agilize.dash.service.dashboardService;

import io.github.cdimascio.dotenv.Dotenv;
import io.micrometer.observation.Observation;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.lambda.thirdparty.org.json.JSONObject;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.PrCountMapper;
import com.br.agilize.dash.mapper.dashboardMapper.TimeColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.VcsPullRequestMapper;

import com.br.agilize.dash.model.dto.dashboardDto.PrCountDto;
import com.br.agilize.dash.model.dto.dashboardDto.VcsPullRequestDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.PrCountEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TimeColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;
import com.br.agilize.dash.model.response.VcsPullRequestResponse;
import com.br.agilize.dash.repository.ColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.PrCountRepository;

import com.br.agilize.dash.repository.dashboardRepository.VcsPullRequestRepository;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VcsPullRequestService implements CommandLineRunner {

    @Autowired
    private VcsPullRequestRepository vcsPullRequestRepository;

    @Autowired
    private VcsPullRequestMapper vcsPullRequestMapper;

    @Autowired
    private PrCountRepository prCountRepository;

    @Autowired
    private PrCountMapper prCountMapper;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private ColaboradorMapper colaboradorMapper;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        getPRDataAndSave();
    }
    
    public void getPRDataAndSave() {
        RestTemplate restTemplate = new RestTemplate();

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        // Endpoints da API REST
        List<String> restApiUrls = Arrays.asList(
                dotenv.get("API_URL_ELASTIC_PR"),
                dotenv.get("API_URL_NB_PR")
        );
        
        // Definindo os cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Hasura-Admin-Secret", dotenv.get("HASURA_ADMIN_SECRET")); // substitua pelo seu admin secret
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        
        List<ColaboradorEntity> colaboradores = colaboradorRepository.findAll();
        
        for (String restApiUrl : restApiUrls) {
           try{
                // Enviando a solicitação  
                HttpEntity<String> request = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(restApiUrl, HttpMethod.GET, request, String.class);
        
                // Altere a desserialização para uma lista de VcsPullRequestResponse
                VcsPullRequestResponse responseDto = mapper.readValue(response.getBody(), VcsPullRequestResponse.class);
                List<VcsPullRequestDto> prDataDtos = responseDto.getVcs_PullRequest();
        
                for (VcsPullRequestDto prDataDto : prDataDtos) {
                    String author = prDataDto.getAuthor().split("\\|")[1];
        
                    for (ColaboradorEntity colaborador : colaboradores) {
                        String githubUsername = colaborador.getGithub();
        
                        if (author.equals(githubUsername)) {
                            
                            String mergedAt = prDataDto.getMergedAt();
                            String title = prDataDto.getTitle();
                            String stateDetail = prDataDto.getStateDetail();
                            String repository = prDataDto.getRepository();
                            
                            // Verificar se a PR já existe no banco de dados
                            Optional<VcsPullRequestEntity> existingPr = vcsPullRequestRepository.findByAuthorAndTitleAndMergedAt(author, title, mergedAt);
                                  
                            // Se a PR não existir no banco de dados, salvá-la
                            if (!existingPr.isPresent()) {
                                VcsPullRequestDto vcsPullRequestDto = new VcsPullRequestDto();
                                vcsPullRequestDto.setMergedAt(mergedAt);
                                vcsPullRequestDto.setAuthor(author);
                                vcsPullRequestDto.setTitle(title);
                                vcsPullRequestDto.setStateDetail(stateDetail);
                                vcsPullRequestDto.setRepository(repository);

                                VcsPullRequestEntity vcsPullRequestEntity = vcsPullRequestMapper.dtoToModel(vcsPullRequestDto);

                                // Buscar o ColaboradorEntity com o nome de usuário do GitHub igual ao autor do PR
                                ColaboradorEntity colaboradorEntity = colaboradorRepository.findByGithub(githubUsername);

                                // Se o ColaboradorEntity for encontrado, definir o campo colaborador do VcsPullRequestEntity para esse ColaboradorEntity
                                if (colaboradorEntity != null) {
                                    vcsPullRequestEntity.setColaborador(colaboradorEntity);
                                }

                                vcsPullRequestRepository.save(vcsPullRequestEntity);
                            }
                        }
                    }
                }
            } catch (RestClientException | JsonProcessingException e) {
                System.err.println("Não foi possível acessar ou processar a URL: " + restApiUrl);
                e.printStackTrace();
        }  
     }
              
     } 
    
    // Query para buscar a quantidade  de PRs de um dev por id dentro de 1 ano 
    public Map<String, Object> getPrCountLast1YearForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLast1YearByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de PRs de um dev por id esse ano
    public Map<String, Object> getPrCountThisYearForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsThisYearByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de PRs de um dev por id ano passado
    public Map<String, Object> getPrCountLastYearForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLastYearByColaboradorId(colaboradorId);
    }
    
    // Query para buscar a quantidade de PRs de um dev por id nos últimos 90 dias
    public Map<String, Object> getPrCountLast90DaysForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLast90DaysByColaboradorId(colaboradorId);
    }

     // Query para buscar a quantidade de PRs de um colaborador por id nos últimos 60 dias
     public Map<String, Object> getPrCountLast60DaysForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLast60DaysByColaboradorId(colaboradorId);
    }
    
    // Query para buscar a quantidade de PRs de um dev por id nos últimos 30 dias
    public Map<String, Object> getPrCountLast30DaysForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLast30DaysByColaboradorId(colaboradorId);
    }
    
    // Query para buscar a quantidade de PRs de um dev por id nos últimos 7 dias
    public Map<String, Object> getPrCountLast7DaysForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countPrsLast7DaysByColaboradorId(colaboradorId);
    }

    // Query para buscar a todos de PRs de um dev por id 
    public Map<String, Object> getAllPrCountForColaborador(Long colaboradorId) {
        return vcsPullRequestRepository.countAllPrsByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de PRs de um dev por id em um intervalo de datas
    public Map<String, Object> getPrCountForColaborador(Long colaboradorId, Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return vcsPullRequestRepository.countPrsInDateRangeByColaboradorId(colaboradorId, startDate, endDate);
        } else {
            // Se as datas não forem fornecidas, retorne a contagem total de PRs
            return vcsPullRequestRepository.countAllPrsByColaboradorId(colaboradorId);
        }
    }

    // Query para buscar a quantidade total de PRs nos últimos 30, 60 e 90 dias
    public Map<String, Object> getPrCountLast30And60And90Days() {
        return vcsPullRequestRepository.countPrsLast30And60And90Days();
    }

    public List<Map<String, Object>> getTop5ColaboradoresByPrs() {
        return vcsPullRequestRepository.findTop5ColaboradoresByPrs();
    }
}