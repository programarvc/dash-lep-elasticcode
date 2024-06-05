package com.br.agilize.dash.controller.dashboardController;

import java.util.Date;
import java.util.Map;

import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.agilize.dash.model.dto.dashboardDto.TasksCountJiraDto;
import com.br.agilize.dash.service.dashboardService.TasksCountJiraService;
import com.br.agilize.dash.service.dashboardService.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "TasksCountJiraController", description = "Api para gerenciar as TasksCountJira")
@Controller
@RequestMapping("/taskscount")
public class TasksCountJiraController {
     
    @Autowired 
    TasksCountJiraService service;
    public TasksCountJiraController(@Autowired TasksCountJiraService service) {
        super();
    }

    /* 
    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity <PrCountDto> getPrCountByColaboradorId(@PathVariable Long colaboradorId) {
        PrCountDto prCountDto = service.getPrCountByColaboradorId(colaboradorId);
        return ResponseEntity.ok(prCountDto);
    }
    */

    @PostMapping("/create")
    public ResponseEntity<TasksCountJiraDto> salvartaskColaborador(@RequestBody TasksCountJiraDto tasksCountJiraDto) {
        try {
            TasksCountJiraDto savedTask = service.salvartaskColaborador(tasksCountJiraDto);
            return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countAllCompletedTasksByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countAllCompletedTasksByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }
    
    @GetMapping("/thisyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countCompletedTasksThisYearByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countCompletedTasksThisYearByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }
    
    @GetMapping("/lastyear/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countCompletedTasksLastYearByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countCompletedTasksLastYearByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }
    
    @GetMapping("/last7days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countCompletedTasksLast7DaysByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countCompletedTasksLast7DaysByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }
    
    @GetMapping("/last30days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countCompletedTasksLast30DaysByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countCompletedTasksLast30DaysByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }

     // Endpoint para buscar a quantidade de tasks concluidas de um colaborador por id nos últimos 60 dias
     @GetMapping("/last60days/{colaboradorId}")
     public ResponseEntity<Map<String, Object>> getCompletedTasksCountLast60DaysForColaborador(@PathVariable Long colaboradorId) {
         Map<String, Object> tasksCount = service.getCompletedTasksCountLast60DaysForColaborador(colaboradorId);
         return ResponseEntity.ok(tasksCount);
     }
    
    @GetMapping("/last90days/{colaboradorId}")
    public ResponseEntity<Map<String, Object>> countCompletedTasksLast90DaysByColaboradorId(@PathVariable Long colaboradorId) {
        Map<String, Object> taskCount = service.countCompletedTasksLast90DaysByColaboradorId(colaboradorId);
        return ResponseEntity.ok(taskCount);
    }

    @GetMapping("/dates/{colaboradorId}")
    @ResponseBody
    public Map<String, Object> getTasksCountForColaborador(
            @PathVariable Long colaboradorId,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        return service.getTasksCountForColaborador(colaboradorId, startDate, endDate);
    }

}
