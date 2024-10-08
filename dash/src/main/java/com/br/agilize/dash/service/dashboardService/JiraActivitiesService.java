package com.br.agilize.dash.service.dashboardService;

import io.github.cdimascio.dotenv.Dotenv;
import io.micrometer.observation.Observation;
import jakarta.persistence.EntityNotFoundException;
import software.amazon.awssdk.services.cognitoidentityprovider.endpoints.internal.Value.Str;


import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.JiraActivitiesMapper;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.JiraActivitiesDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.JiraActivitiesEntity;
import com.br.agilize.dash.model.response.JiraActivitiesResponse;
import com.br.agilize.dash.repository.ColaboradorRepository;

import com.br.agilize.dash.repository.dashboardRepository.JiraActivitiesRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
@Service
public class JiraActivitiesService implements CommandLineRunner {

    @Autowired
    private JiraActivitiesRepository repository;

    @Autowired
    private JiraActivitiesMapper jiraActivitiesMapper;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        getJiraDataAndSave();
    }

    public void getJiraDataAndSave() {
        RestTemplate restTemplate = new RestTemplate();
    
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    
        String restApiUrl = dotenv.get("API_URL_ELASTIC_JIRA");
    
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Hasura-Admin-Secret", dotenv.get("HASURA_ADMIN_SECRET"));
    
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
        int maxTentativas = 3;
        for (int tentativa = 1; tentativa <= maxTentativas; tentativa++) {
            try {
                HttpEntity<String> request = new HttpEntity<>(headers);
                ResponseEntity<String> response = restTemplate.exchange(restApiUrl, HttpMethod.GET, request, String.class);
    
                JiraActivitiesResponse responseDto = mapper.readValue(response.getBody(), JiraActivitiesResponse.class);
                List<JiraActivitiesDto> jiraDataDtos = responseDto.getTms_Task();
    
                for (JiraActivitiesDto jiraDataDto : jiraDataDtos) {
                    saveJiraActivityIfNotExists(jiraDataDto);
                }
                break; // Sai do loop se a operação for bem-sucedida
            } catch (HttpClientErrorException | HttpServerErrorException e) {
                System.err.println("Erro de cliente ou servidor ao acessar: " + restApiUrl + ". Tentativa " + tentativa + " de " + maxTentativas);
                e.printStackTrace();
            } catch (ResourceAccessException e) {
                System.err.println("Falha de acesso ao recurso: " + restApiUrl + ". Tentativa " + tentativa + " de " + maxTentativas);
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                System.err.println("Erro ao processar JSON da resposta.");
                e.printStackTrace();
                break; // Sai do loop se houver um problema de processamento de JSON, pois é provável que seja um erro persistente
            } catch (Exception e) {
                System.err.println("Erro inesperado ao acessar: " + restApiUrl);
                e.printStackTrace();
                break; // Sai do loop para erros inesperados
            }
            // Implementar espera exponencial ou fixa aqui, se necessário
        }
    }
    
    private void saveJiraActivityIfNotExists(JiraActivitiesDto jiraDataDto) {
        String epic = jiraDataDto.getEpic();
        String name = jiraDataDto.getName();
        String parent = jiraDataDto.getParent();
        String priority = jiraDataDto.getPriority();
        String sprint = jiraDataDto.getSprint();
        String typeDetail = jiraDataDto.getTypeDetail();
        String statusDetail = jiraDataDto.getStatusDetail();
        String points = jiraDataDto.getPoints();
        String createdAt = jiraDataDto.getCreatedAt();
        String source = jiraDataDto.getSource();
        String updatedAt = jiraDataDto.getUpdatedAt();
        String tmsUserName = jiraDataDto.getTmsUserName();
        String tmsUserId = jiraDataDto.getTmsUserId();


        // Verificar se a atividade já existe no banco de dados
        Optional<JiraActivitiesEntity> existingActivity = repository.findByNameAndSprintAndPriorityAndUpdatedAtAndTypeDetailAndSource(name, sprint, priority, updatedAt, typeDetail, source);
    
        // Se a atividade não existir no banco de dados, salvá-la
        if (!existingActivity.isPresent()) {
            JiraActivitiesEntity jiraActivitiesEntity = new JiraActivitiesEntity();
            jiraActivitiesEntity.setEpic(epic);
            jiraActivitiesEntity.setName(name);
            jiraActivitiesEntity.setParent(parent);
            jiraActivitiesEntity.setPriority(priority);
            jiraActivitiesEntity.setSprint(sprint);
            jiraActivitiesEntity.setTypeDetail(typeDetail);
            jiraActivitiesEntity.setStatusDetail(statusDetail);
            jiraActivitiesEntity.setPoints(points);
            jiraActivitiesEntity.setCreatedAt(createdAt);
            jiraActivitiesEntity.setSource(source);
            jiraActivitiesEntity.setUpdatedAt(updatedAt);
            jiraActivitiesEntity.setTmsUserName(tmsUserName);
            jiraActivitiesEntity.setTmsUserId(tmsUserId);

            // Convertendo a entidade para DTO usando o Mapper
            JiraActivitiesDto newJiraDataDto = jiraActivitiesMapper.modelToDTO(jiraActivitiesEntity);
    
            // Convertendo o DTO de volta para a entidade para salvar no banco de dados
            JiraActivitiesEntity entityToSave = jiraActivitiesMapper.dtoToModel(newJiraDataDto);
    
            repository.save(entityToSave);
        }
    }

    // Método para deletar uma atividade
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Método para buscar todas as atividades
    public List<JiraActivitiesDto> findAll() {
        return repository.findAll().stream()
            .map(jiraActivitiesMapper::modelToDTO)
            .collect(Collectors.toList());
    }

    // Método para buscar uma atividade por id
    public Optional<JiraActivitiesDto> findById(Long id) {
        return repository.findById(id)
            .map(jiraActivitiesMapper::modelToDTO);
    }

    // Método quantidade de historias de um epico especifico
    public Map<String, Object> countStories() {
        try{
        return repository.countStories();
        } catch  (Exception e) {
            System.out.println("Erro ao contar histórias: " + e.getMessage());
            return new HashMap<>();
        }
    }


    public List<Map<String, Object>> countAndDetailsByTypeDetail() {
        try {
        return repository.countAndDetailsByTypeDetail();
        } catch (Exception e) {
        System.out.println("Erro ao contar e detalhar por tipo: " + e.getMessage());
        return new ArrayList<>();
        }
    }

    public Map<String, Object> getCountEpics() {
        try {
            return repository.countEpics();
        } catch (Exception e) {
            System.out.println("Erro ao contar épicos: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Método para contar todas as tarefas
    public Map<String, Object> countAllStories() {
        try {
            return repository.countAllStories();
        } catch (Exception e) {
            System.out.println("Erro ao contar todas as histórias: " + e.getMessage());
            return new HashMap<>();
        }
    }

    // Método para contar todas as tarefas dos últimos 60 dias
    public Map<String, Object> countAllStoriesLast60Days() {
        try {
            return repository.countAllStoriesLast60Days();
        } catch (Exception e) {
            System.out.println("Erro ao contar todas as histórias dos últimos 60 dias: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public double averageStoriesPerEpic() {
        try {
            Map<String, Object> storiesResult = repository.countAllStories();
            Map<String, Object> epicsResult = repository.countAllEpics();
    
            Long totalStories = ((Number) storiesResult.get("story_count")).longValue();
            Long totalEpics = ((Number) epicsResult.get("count_epics")).longValue();
    
            if (totalEpics == 0) {
                return 0;
            }
    
            return (double) totalStories / totalEpics;
        } catch (Exception e) {
            System.out.println("Erro ao calcular a média de histórias por épico: " + e.getMessage());
            return 0;
        }
    }

    public Map<String, Object> calculateAveragePoints() {
        try {
            return repository.calculateTotalAndAveragePoints();
        } catch (Exception e) {
            System.out.println("Erro ao calcular pontos médios: " + e.getMessage());
            return new HashMap<>();
        }
    }
    
    public Map<String, Object> getSumTotalPointsForJiraStories() {
        try {
            return repository.sumTotalPointsForJiraStories();
        } catch (Exception e) {
            System.out.println("Erro ao buscar total de pontos: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public Map<String, Object> getTotalPointsForJiraStoriesLast60Days() {
        try {
            return repository.sumTotalPointsForJiraStoriesLast60Days();
        } catch (Exception e) {
            System.out.println("Erro ao buscar total de pontos nos últimos 60 dias: " + e.getMessage());
            return new HashMap<>();
        }
    }


    //Método para reotrnar atividades disponíveis no Jira
    public List<JiraActivitiesDto> findAvailableActivities() {
        return repository.findAvailableActivities().stream()
                .map(jiraActivitiesMapper::modelToDTO)
                .collect(Collectors.toList());
    }

    // Método para retornar as atividades de acordo com o epico especificado
    public List<Map<String, Object>> findNameAndPointForEpicEqualsParent(String epic) {
        return repository.findNameAndPointForEpicEqualsParent(epic);
    }

    // Método para retornar o nome e pontos das atividades
    public List<Map<String, Object>> findNameAndPoints() {
        return repository.findNameAndPoints();
    }

    //método para retornar atividades concluídas
    public List<JiraActivitiesDto> findCompletedActivities() {
        return repository.findCompletedActivities().stream()
                .map(jiraActivitiesMapper::modelToDTO)
                .collect(Collectors.toList());
    }
}