package com.br.agilize.dash.model.response;

import java.util.List;

import com.br.agilize.dash.model.dto.dashboardDto.JiraActivitiesDto;

import lombok.Data;

@Data
public class JiraActivitiesResponse {

    private List<JiraActivitiesDto> tms_Task;

    // getters e setters...
}