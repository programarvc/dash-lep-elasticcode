package com.br.agilize.dash.model.dto.dashboardDto;

import java.time.LocalDateTime;

import com.br.agilize.dash.model.dto.ColaboradorDto;

import lombok.Data;

@Data
public class TasksCountJiraDto {

    private Long id;

    private String taskName;

    private String statusDetail;

    private LocalDateTime mergedAt;

    private String author;  

}