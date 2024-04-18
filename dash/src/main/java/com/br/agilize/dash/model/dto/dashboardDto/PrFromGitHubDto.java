package com.br.agilize.dash.model.dto.dashboardDto;


import lombok.Data;
import java.util.List;

@Data
public class PrFromGitHubDto {
    private Long id;
    private String username;
    private Integer prCount;
    private List<String> prDates;
}
