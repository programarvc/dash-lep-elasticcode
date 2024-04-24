package com.br.agilize.dash.model.dto.dashboardDto;


import lombok.Data;
import java.util.List;

@Data
public class PrFromGitHubDto {
    private Long id;
    private String prAuthor;
    private String createdAt;
    private String mergedAt;
    private String repoName;
}
