package com.br.agilize.dash.service.dashboardService;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.PromptsHistoryMapper;
import com.br.agilize.dash.model.dto.dashboardDto.PromptsHistoryDto;
import com.br.agilize.dash.model.entity.dashboardEntity.JiraActivitiesEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.PromptsHistoryEntity;
import com.br.agilize.dash.repository.dashboardRepository.JiraActivitiesRepository;
import com.br.agilize.dash.repository.dashboardRepository.PromptsHistoryRepository;
import com.br.agilize.dash.service.ServiceCrudBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class PromptsHistoryService extends ServiceCrudBase<PromptsHistoryDto> {
    @Autowired
    private PromptsHistoryRepository repository;

    @Autowired
    private PromptsHistoryMapper mapper;

    @Autowired
    private JiraActivitiesRepository jiraActivitiesRepository;

    @Override
    public PromptsHistoryDto obterPorId(Long id){
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(() -> new DashNotFoundException("Prompt history not found")));
    }

    @Override
    public List<PromptsHistoryDto> obterTodos() {
        List<PromptsHistoryEntity> promptsHistories = this.repository.findAll();
        return promptsHistories.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public PromptsHistoryDto salvar(PromptsHistoryDto payload) {
        PromptsHistoryEntity entity = this.mapper.dtoToModel(payload);

        // Associar jiraActivity
        if (payload.getJiraActivity() != null && payload.getJiraActivity().getId() != null) {
            JiraActivitiesEntity jiraActivity = jiraActivitiesRepository.findById(payload.getJiraActivity().getId())
                    .orElseThrow(() -> new DashNotFoundException("JiraActivity not found"));
            entity.setJiraActivity(jiraActivity);
        }

        PromptsHistoryEntity promptsHistorySalvo = this.repository.save(entity);
        return this.mapper.modelToDTO(promptsHistorySalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public Long countByUserEsteiraId(Long userEsteiraId) {
        return this.repository.countByUserEsteiraId(userEsteiraId);
    }

    public List<PromptsHistoryDto> findByUserEsteiraId(Long userEsteiraId) {
        List<PromptsHistoryEntity> promptsHistories = this.repository.findByUserEsteiraId(userEsteiraId);
        return promptsHistories.stream().map(this.mapper::modelToDTO).toList();
    }

    public List<PromptsHistoryDto> getPromptsByEsteiraId(Long esteiraId) {
        List<PromptsHistoryEntity> promptsHistories = this.repository.findByEsteiraId(esteiraId);
        return promptsHistories.stream().map(this.mapper::modelToDTO).toList();
    }

    public Long countByEsteiraId(Long esteiraId) {
        return this.repository.countByEsteiraId(esteiraId);
    }
}
