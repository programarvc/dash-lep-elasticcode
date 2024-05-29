package com.br.agilize.dash.controller.dashboardController;

import java.util.Date;
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

}
