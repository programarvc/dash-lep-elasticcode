package com.br.agilize.dash.service.dashboardService;

import io.github.cdimascio.dotenv.Dotenv;
import io.micrometer.observation.Observation;
import jakarta.persistence.EntityNotFoundException;
import software.amazon.awssdk.services.cognitoidentityprovider.endpoints.internal.Value.Str;

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
   
    public void run(String... args) throws Exception {
        getJiraDataAndSave();
    }

// Método para salvar uma atividade

    @Transactional
    public void getJiraDataAndSave() {
        RestTemplate restTemplate = new RestTemplate();
    
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    
        // Endpoint da API REST
        String restApiUrl = dotenv.get("API_URL_ELASTIC_JIRA");
    
        // Definindo os cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Hasura-Admin-Secret", dotenv.get("HASURA_ADMIN_SECRET")); // substitua pelo seu admin secret
    
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    
        try {
            // Enviando a solicitação  
            HttpEntity<String> request = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(restApiUrl, HttpMethod.GET, request, String.class);
    
            // Desserializar a resposta para uma lista de JiraActivitiesDto
            JiraActivitiesResponse responseDto = mapper.readValue(response.getBody(), JiraActivitiesResponse.class);
            List<JiraActivitiesDto> jiraDataDtos = responseDto.getTms_Task();
    
            for (JiraActivitiesDto jiraDataDto : jiraDataDtos) {
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
                
                // Verificar se a atividade já existe no banco de dados
                Optional<JiraActivitiesEntity> existingActivity = repository.findByNameAndSprintAndPriorityAndUpdatedAtAndTypeDetailAndSource(name, sprint, priority, updatedAt, typeDetail, source);

                
                // Se a atividade não existir no banco de dados, salvá-la
                if (!existingActivity.isPresent()) {
                    jiraDataDto = new JiraActivitiesDto();
                    jiraDataDto.setEpic(epic);
                    jiraDataDto.setName(name);
                    jiraDataDto.setParent(parent);
                    jiraDataDto.setPriority(priority);
                    jiraDataDto.setSprint(sprint);
                    jiraDataDto.setTypeDetail(typeDetail);
                    jiraDataDto.setStatusDetail(statusDetail);
                    jiraDataDto.setPoints(points);
                    jiraDataDto.setCreatedAt(createdAt);
                    jiraDataDto.setSource(source);
                    jiraDataDto.setUpdatedAt(updatedAt);
                    JiraActivitiesEntity jiraActivitiesEntity = jiraActivitiesMapper.dtoToModel(jiraDataDto);
                    repository.save(jiraActivitiesEntity);
                }
            }
        } catch (RestClientException | JsonProcessingException e) {
            System.err.println("Não foi possível acessar ou processar a URL: " + restApiUrl);
            e.printStackTrace();
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
}