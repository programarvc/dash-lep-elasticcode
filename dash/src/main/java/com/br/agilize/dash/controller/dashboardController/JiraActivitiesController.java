package com.br.agilize.dash.controller.dashboardController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.agilize.dash.model.dto.dashboardDto.JiraActivitiesDto;
import com.br.agilize.dash.service.dashboardService.JiraActivitiesService;
import com.br.agilize.dash.service.dashboardService.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "JiraActivitiesController", description = "Api para gerenciar as JiraActivities")
@Controller
@RequestMapping("/jira")
public class JiraActivitiesController {
     
    @Autowired 
    JiraActivitiesService service;
    
    public JiraActivitiesController(@Autowired JiraActivitiesService service) {
        super();
    }

    @GetMapping("/all")
    public ResponseEntity<List<JiraActivitiesDto>> getAll() {
        List<JiraActivitiesDto> activities = service.findAll();
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/countstory")
    public ResponseEntity<Map<String, Object>> countStories() {
        Map<String, Object> count = service.countStories();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/countepics")
    public ResponseEntity<List<Map<String, Object>>> countAndDetailsByTypeDetail() {
        List<Map<String, Object>> details = service.countAndDetailsByTypeDetail();
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @GetMapping("/countepicslast60days")
    public ResponseEntity<Map<String, Object>> getCountEpics() {
        Map<String, Object> result = service.getCountEpics();
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/countAllActivities")
    public ResponseEntity<Map<String, Object>> countAllStories() {
        Map<String, Object> result = service.countAllStories();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/countAllActivities/last60days")
    public ResponseEntity<Map<String, Object>> getAllStoriesLast60Days() {
        Map<String, Object> result = service.countAllStoriesLast60Days();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/mediastoryperepic")
    public ResponseEntity<Map<String, Double>> getAverageStoriesPerEpic() {
        double average = service.averageStoriesPerEpic();
        Map<String, Double> response = new HashMap<>();
        response.put("media_stories", average);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/averagepoints")
    public ResponseEntity<Map<String, Object>> getAveragePoints() {
        return ResponseEntity.ok(service.calculateAveragePoints());
    }

    @GetMapping("/allStoryPoints")
    public ResponseEntity<Map<String, Object>> getTotalPointsForJiraStories() {
        return ResponseEntity.ok(service.getSumTotalPointsForJiraStories());
    }

    @GetMapping("/allStoryPointsLast60days")
    public ResponseEntity<Map<String, Object>> getTotalPointsForJiraStoriesLast60Days() {
        return ResponseEntity.ok(service.getTotalPointsForJiraStoriesLast60Days());
    }

    @GetMapping("/atividades-disponiveis")
    public ResponseEntity<List<JiraActivitiesDto>> findAvailableActivities() {
        return ResponseEntity.ok(service.findAvailableActivities());
    }

    @GetMapping("/epicslistacttivities")
    public ResponseEntity<List<Map<String, Object>>> findNameAndPointForEpicEqualsParent(@RequestParam String epic) {
        List<Map<String, Object>> activities = service.findNameAndPointForEpicEqualsParent(epic);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/namepoints")
    public ResponseEntity<List<Map<String, Object>>> getNameAndPoints() {
        List<Map<String, Object>> result = service.findNameAndPoints();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/completedActivities")
    public ResponseEntity<List<JiraActivitiesDto>> getCompletedActivities() {
        List<JiraActivitiesDto> completedActivities = service.findCompletedActivities();
        return ResponseEntity.ok(completedActivities);
    }
}
