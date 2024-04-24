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
    private VcsPullRequestRepository metaBaseRepository;

    @Autowired
    private VcsPullRequestMapper metaBaseMapper;

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

       /*  RestTemplate restTemplate = new RestTemplate();

        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

        // Endpoints da API REST
        List<String> restApiUrls = Arrays.asList(
                dotenv.get("API_URL_ELASTIC_PR"),
                dotenv.get("API_URL_NB_PR")
        );


        // Definindo os cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Hasura-Admin-Secret", dotenv.get("HASURA_ADMIN_SECRET")); // substitua  pelo seu admin secret

        for (String restApiUrl : restApiUrls) {
            // Enviando a solicitação
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(restApiUrl, HttpMethod.GET, request, String.class);

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            try {
                // Altere a desserialização para uma lista de VcsPullRequestResponse
                VcsPullRequestResponse responseDto = mapper.readValue(response.getBody(), VcsPullRequestResponse.class);
                List<VcsPullRequestDto> prDataDtos = responseDto.getVcs_PullRequest();

                Map<String, Integer> authorPrCount = new HashMap<>();

                // Itere sobre a lista de DTOs
                for (VcsPullRequestDto prDataDto : prDataDtos) {
                    String authorName = prDataDto.getAuthor().split("\\|")[1];
                    prDataDto.setAuthor(authorName);

                    // Incrementa a contagem para o autor no mapa
                    authorPrCount.put(authorName, authorPrCount.getOrDefault(authorName, 0) + 1);

                    // Cria ou atualiza um PrCountDto para o autor
                    ColaboradorEntity colaborador = colaboradorRepository.findByGithub(authorName);
                    if (colaborador != null && colaborador.getId() != null) {
                        PrCountEntity prCountEntity = prCountRepository.findByColaborador(colaborador);
                        PrCountDto prCountDto;
                        if (prCountEntity == null) {
                            prCountDto = new PrCountDto();
                            prCountDto.setColaborador(colaboradorMapper.modelToDTO(colaborador));
                        } else {
                            prCountDto = prCountMapper.modelToDTO(prCountEntity);
                        }
                        prCountDto.setCount(authorPrCount.get(authorName));

                        // Salva o PrCountDto no banco de dados
                        prCountRepository.save(prCountMapper.dtoToModel(prCountDto));
                    }

                    VcsPullRequestEntity prData = metaBaseMapper.dtoToModel(prDataDto);


                    if(prData.getMergedAt() != null && prData.getAuthor() != null && prData.getTitle() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
                        LocalDateTime dateTime = LocalDateTime.parse(prData.getMergedAt(), formatter);
                        // Verifica se já existe um registro com o mesmo author, title e mergedAt
                        Optional<VcsPullRequestEntity> existingPrData = metaBaseRepository.findByAuthorAndTitleAndMergedAt(prData.getAuthor(), prData.getTitle(), prData.getMergedAt());

                        // Se o registro não existir, salva no banco de dados
                        if (!existingPrData.isPresent()) {
                            metaBaseRepository.save(prData);
                        }
                    }
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }*/
    }
    /*@Transactional
    public PrCountDto getPrCountByColaboradorId(Long colaboradorId) {
        PrCountEntity prCountEntity = this.prCountRepository.findByColaboradorId(colaboradorId);
        if (prCountEntity == null) {
            PrCountDto prCountDto = new PrCountDto();
            prCountDto.setCount(0);
            return prCountDto;
        } else {
            return prCountMapper.modelToDTO(prCountEntity);
        }
    }*/
}