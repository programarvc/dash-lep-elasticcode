package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;

@Data
public class PromptJiraActivityDto {
    private Long id;
    private PromptsHistoryDto prompt;
    private JiraActivitiesDto jiraActivity;
}
