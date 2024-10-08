package com.br.agilize.dash.model.entity.dashboardEntity;

import java.time.LocalDateTime;

import com.br.agilize.dash.model.entity.ColaboradorEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "tms_Task")
@NoArgsConstructor
@AllArgsConstructor
public class JiraActivitiesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "epic", columnDefinition = "TEXT")
    private String epic;

    @Column(name = "parent", columnDefinition = "TEXT")
    private String parent;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "priority", columnDefinition = "TEXT")
    private String priority;

    @Column(name = "sprint", columnDefinition = "TEXT")
    private String sprint;

    @Column(name = "type_detail", columnDefinition = "TEXT")
    private String typeDetail;

    @Column(name = "status_detail", columnDefinition = "TEXT")
    private String statusDetail;

    @Column(name = "points", columnDefinition = "TEXT")
    private String points;

    @Column(name = "created_at", columnDefinition = "TEXT")
    private String createdAt;

    @Column(name = "source", columnDefinition = "TEXT")
    private String source;

    @Column(name = "updated_at", columnDefinition = "TEXT")
    private String updatedAt;

    @Column(name = "tms_user_name", columnDefinition = "TEXT")
    private String tmsUserName;

    @Column(name = "tms_user_id", columnDefinition = "TEXT")
    private String tmsUserId;

}