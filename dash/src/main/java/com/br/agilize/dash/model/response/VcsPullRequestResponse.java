package com.br.agilize.dash.model.response;

import java.util.List;

import com.br.agilize.dash.model.dto.dashboardDto.VcsPullRequestDto;

import lombok.Data;

@Data
public class VcsPullRequestResponse {

    private List<VcsPullRequestDto> vcs_PullRequest;

    // getters e setters...
}