package com.br.agilize.dash.model.dto.dashboardDto;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

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

    private String tmsUserName;

    private String tmsUserId;

    @JsonProperty("tms_User")
    private void unpackNested(Map<String,Object> tms_User) {
        this.tmsUserName = (String) tms_User.get("name");
        this.tmsUserId = (String) tms_User.get("id");
    }

}