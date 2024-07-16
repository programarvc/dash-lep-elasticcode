package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class JiraActivitiesDto {
    private Long id;
    private String epic;
    private String parent;
    private String name;
    private String priority;
    private String sprint;
    private String statusDetail;
    private String typeDetail;
    private String points;
    private String createdAt;
    private String source;
    private String updatedAt;
}