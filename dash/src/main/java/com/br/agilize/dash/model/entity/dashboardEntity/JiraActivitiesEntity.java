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

    @Column(name = "status_modifided", columnDefinition = "TEXT")
    private String statusModifided;

}