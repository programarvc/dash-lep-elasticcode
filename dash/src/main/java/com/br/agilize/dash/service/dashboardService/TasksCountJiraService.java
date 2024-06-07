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

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.ColaboradorMapper;
import com.br.agilize.dash.mapper.dashboardMapper.TasksCountJiraMapper;
import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.model.dto.dashboardDto.TasksCountJiraDto;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.TasksCountJiraEntity;
import com.br.agilize.dash.repository.ColaboradorRepository;

import com.br.agilize.dash.repository.dashboardRepository.TasksCountJiraRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TasksCountJiraService implements CommandLineRunner {

    @Autowired
    private TasksCountJiraRepository repository;

    @Autowired
    private TasksCountJiraMapper mapper;

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

    }

    public TasksCountJiraDto salvartaskColaborador(TasksCountJiraDto tasksCountJiraDto) {
        // Buscar o ColaboradorEntity com o nome de usuário do GitHub igual ao autor do PR
        ColaboradorEntity colaboradorEntity = colaboradorRepository.findByGithub(tasksCountJiraDto.getAuthor());
    
        // Se o ColaboradorEntity for encontrado, definir o campo colaborador do TasksCountJiraEntity para esse ColaboradorEntity
        if (colaboradorEntity != null) {
            TasksCountJiraEntity existingTask = repository.findByAuthorAndStatusDetailAndMergedAt(tasksCountJiraDto.getAuthor(), tasksCountJiraDto.getStatusDetail(), tasksCountJiraDto.getMergedAt());
    
            // Se já existir um TasksCountJiraEntity com os mesmos dados, retorne o DTO correspondente
            if (existingTask != null) {
                return this.mapper.modelToDTO(existingTask);
            }
    
            // Se não existir, crie um novo TasksCountJiraEntity e salve-o
            TasksCountJiraEntity tasksCountJiraEntity = this.mapper.dtoToModel(tasksCountJiraDto);
            tasksCountJiraEntity.setColaborador(colaboradorEntity);
            return this.mapper.modelToDTO(this.repository.save(tasksCountJiraEntity));
        } else {
            throw new EntityNotFoundException("Não existe colaborador com esse GitHub username: " + tasksCountJiraDto.getAuthor());
        }
    }
    
    public List<TasksCountJiraDto> obterTodos() {
        List<TasksCountJiraEntity> tasksCountJira = this.repository.findAll();

        return tasksCountJira.stream().map(this.mapper::modelToDTO).toList();
    }

    
    public TasksCountJiraDto salvar(TasksCountJiraDto payload) {
        TasksCountJiraEntity tasksCountJiraSalvo = this.repository.save(this.mapper.dtoToModel(payload));
        return this.mapper.modelToDTO(tasksCountJiraSalvo);
    }


    
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
    
    public Map<String, Object> countAllCompletedTasksByColaboradorId(Long colaboradorId) {
        return repository.countAllCompletedTasksByColaboradorId(colaboradorId);
    }

    public Map<String, Object> countCompletedTasksThisYearByColaboradorId(Long colaboradorId) {
        return repository.countCompletedTasksThisYearByColaboradorId(colaboradorId);
    }

    public Map<String, Object> countCompletedTasksLastYearByColaboradorId(Long colaboradorId) {
        return repository.countCompletedTasksLastYearByColaboradorId(colaboradorId);
    }

    public Map<String, Object> countCompletedTasksLast7DaysByColaboradorId(Long colaboradorId) {
        return repository.countCompletedTasksLast7DaysByColaboradorId(colaboradorId);
    }

    public Map<String, Object> countCompletedTasksLast30DaysByColaboradorId(Long colaboradorId) {
        return repository.countCompletedTasksLast30DaysByColaboradorId(colaboradorId);
    }

    public Map<String, Object> countCompletedTasksLast90DaysByColaboradorId(Long colaboradorId) {
        return repository.countCompletedTasksLast90DaysByColaboradorId(colaboradorId);
    }

    // Query para buscar a quantidade de tarefas concluídas de um colaborador por id em um intervalo de datas
    public Map<String, Object> getTasksCountForColaborador(Long colaboradorId, Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return repository.countCompletedTasksInDateRangeByColaboradorId(colaboradorId, startDate, endDate);
        } else {
            // Se as datas não forem fornecidas, retorne a contagem total de tarefas concluídas
            return repository.countAllCompletedTasksByColaboradorId(colaboradorId);
        }
    }

     // Query para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 60 dias
     public Map<String, Object> getCompletedTasksCountLast60DaysForColaborador(Long colaboradorId) {
        return repository.countCompletedTasksLast60DaysByColaboradorId(colaboradorId);
    }

}