package com.br.agilize.dash.service.dashboardService;

import com.br.agilize.dash.mapper.dashboardMapper.PrFromGitHubMapper;
import com.br.agilize.dash.model.dto.dashboardDto.PrFromGitHubDto;
import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;
import com.br.agilize.dash.repository.dashboardRepository.PrFromGitHubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;

public class PrsGitHubApiService {

    private final Dotenv dotenv;
    private final PrFromGitHubRepository prFromGitHubRepository;

    @Autowired
    private PrFromGitHubMapper prFromGitHubMapper;
    @Autowired
    public PrsGitHubApiService(PrFromGitHubRepository prFromGitHubRepository) {
        dotenv = Dotenv.load();
        this.prFromGitHubRepository = prFromGitHubRepository;
    }

    public int getPrCountByUser(String username) {
        RestTemplate restTemplate = new RestTemplate();
        String repoOwner = dotenv.get("REPO_OWNER");
        String repoName = dotenv.get("REPO_NAME");
        String url = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/pulls?state=all&per_page=100";
    
        ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
    
        List<Map<String, Object>> pullRequests = response.getBody();
    
        int count = 0;
        List<String> dates = new ArrayList<>();
    
        for(Map<String, Object> pr : pullRequests) {
            Map<String, String> user = (Map<String, String>) pr.get("user");
            if(user.get("login").equals(username)) {
                count++;
                dates.add((String) pr.get("created_at"));
            }
        }
    
        PrFromGitHubDto prFromGitHubDto = new PrFromGitHubDto();
        prFromGitHubDto.setUsername(username);
        prFromGitHubDto.setPrCount(count);
        prFromGitHubDto.setPrDates(dates);
    
        PrFromGitHubEntity prFromGitHubEntity = prFromGitHubMapper.dtoToModel(prFromGitHubDto);
    
        prFromGitHubRepository.save(prFromGitHubEntity);
    
        return count;
    }
}
