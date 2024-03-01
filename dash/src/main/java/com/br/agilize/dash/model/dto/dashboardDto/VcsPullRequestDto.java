package com.br.agilize.dash.model.dto.dashboardDto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class VcsPullRequestDto {

    private Long id;

    private String title;

    private String mergedAt;

    private String author;  

    
}