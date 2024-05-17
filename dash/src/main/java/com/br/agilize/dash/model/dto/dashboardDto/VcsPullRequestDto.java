package com.br.agilize.dash.model.dto.dashboardDto;

import com.br.agilize.dash.model.dto.ColaboradorDto;

import lombok.Data;

@Data
public class VcsPullRequestDto {

    private Long id;

    private String title;

    private String mergedAt;

    private String author;    
}