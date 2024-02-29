package com.br.agilize.dash.service.dashboardService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.amazonaws.lambda.thirdparty.org.json.JSONObject;
import com.br.agilize.dash.mapper.dashboardMapper.VcsPullRequestMapper;
import com.br.agilize.dash.model.dto.dashboardDto.VcsPullRequestDto;
import com.br.agilize.dash.model.entity.dashboardEntity.VcsPullRequestEntity;
import com.br.agilize.dash.model.response.VcsPullRequestResponse;
import com.br.agilize.dash.repository.dashboardRepository.VcsPullRequestRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VcsPullRequestService implements CommandLineRunner {

    @Autowired
    private VcsPullRequestRepository metaBaseRepository;

    @Autowired
    private VcsPullRequestMapper metaBaseMapper;

    @Override
    public void run(String... args) throws Exception {
        getPRDataAndSave();
    }

    public void getPRDataAndSave() {
        RestTemplate restTemplate = new RestTemplate();

        // Endpoint da API REST
        String restApiUrl = "http://3.22.183.218:8080/api/rest/pullrequest";

        // Definindo os cabeçalhos
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Hasura-Admin-Secret", "admin"); // substitua "admin" pelo seu admin secret

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

             // Itere sobre a lista de DTOs
             for (VcsPullRequestDto prDataDto : prDataDtos) {
                 VcsPullRequestEntity prData = metaBaseMapper.dtoToModel(prDataDto);
                 metaBaseRepository.save(prData);
             }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}