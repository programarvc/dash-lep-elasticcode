package com.br.agilize.dash.service.dashboardService;

//Service que busca os dados de pr da API do GitHub e salva no banco de dados
import com.br.agilize.dash.mapper.dashboardMapper.PrFromGitHubMapper;
import com.br.agilize.dash.model.dto.dashboardDto.PrFromGitHubDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.PrFromGitHubEntity;
import com.br.agilize.dash.repository.ColaboradorRepository;
import com.br.agilize.dash.repository.dashboardRepository.PrFromGitHubRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;

import io.github.cdimascio.dotenv.Dotenv;
 
@Service
public class PrsGitHubApiService  implements CommandLineRunner{

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    
    @Autowired
    private PrFromGitHubRepository prFromGitHubRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private PrFromGitHubMapper prFromGitHubMapper;
   
   

    @Override
    public void run(String... args) throws Exception {
        getPrCountByUser();
    }
    
    @Scheduled(cron = "0 0 */12 * * ?")
    public void getPrCountByUser() {
        List<ColaboradorEntity> colaboradores = colaboradorRepository.findAll();
    
        for (ColaboradorEntity colaborador : colaboradores) {
            String githubUsername = colaborador.getGithub();
    
            RestTemplate restTemplate = new RestTemplate();
            String RepoOwner = dotenv.get("REPO_OWNER");
            String RepoName = dotenv.get("REPO_NAME");
            String url = "https://api.github.com/repos/" + RepoOwner + "/" + RepoName + "/pulls?state=all&per_page=100";
    
            ResponseEntity<List> response = restTemplate.exchange(url, HttpMethod.GET, null, List.class);
    
            List<Map<String, Object>> pullRequests = response.getBody();
    
            for(Map<String, Object> pr : pullRequests) {
                Map<String, String> user = (Map<String, String>) pr.get("user");
                if(user.get("login").equals(githubUsername)) {
    
                    String createdAt = (String) pr.get("created_at");
                    String mergedAt = (String) pr.get("merged_at");
                    // Verificar se a PR já existe no banco de dados
                    PrFromGitHubEntity existingPr = prFromGitHubRepository.findByPrAuthorAndCreatedAtAndMergedAt(githubUsername, createdAt, mergedAt);
    
                    // Se a PR não existir no banco de dados, salvá-la
                    if (existingPr == null) {
                        PrFromGitHubDto prFromGitHubDto = new PrFromGitHubDto();
                        prFromGitHubDto.setCreatedAt(createdAt);
                        prFromGitHubDto.setMergedAt(mergedAt);
                        prFromGitHubDto.setPrAuthor(githubUsername);
                        Map<String, Object> base = (Map<String, Object>) pullRequests.get(0).get("base");
                        Map<String, Object> repo = (Map<String, Object>) base.get("repo");
                        prFromGitHubDto.setRepoName((String) repo.get("full_name"));
    
                        PrFromGitHubEntity prFromGitHubEntity = prFromGitHubMapper.dtoToModel(prFromGitHubDto);
    
                        // Buscar o ColaboradorEntity com o nome de usuário do GitHub igual ao autor do PR
                        ColaboradorEntity colaboradorEntity = colaboradorRepository.findByGithub(githubUsername);
    
                        // Se o ColaboradorEntity for encontrado, definir o campo colaborador do PrFromGitHubEntity para esse ColaboradorEntity
                        if (colaboradorEntity != null) {
                            prFromGitHubEntity.setColaborador(colaboradorEntity);
                        }
    
                        prFromGitHubRepository.save(prFromGitHubEntity);
                    }
                }
            }
        }
    }

    // Query para buscar a quantidade total de PRs de um dev por id
    public Map<String, Object> getPrCountForColaborador(Long colaboradorId) {
        return prFromGitHubRepository.countByColaboradorId(colaboradorId);
    }
    
    // Query para buscar a quantidade de PRs de um dev por id nos últimos 90 dias
    public Map<String, Object> getPrCountLast90DaysForColaborador(Long colaboradorId) {
        return prFromGitHubRepository.countPrsLast90DaysByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de PRs de um dev por id nos últimos 30 dias
    public Map<String, Object> getPrCountLast30DaysForColaborador(Long colaboradorId) {
        return prFromGitHubRepository.countPrsLast30DaysByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de PRs de um dev por id nos últimos 7 dias
    public Map<String, Object> getPrCountLast7DaysForColaborador(Long colaboradorId) {
        return prFromGitHubRepository.countPrsLast7DaysByColaboradorId(colaboradorId);
    }

    
}
