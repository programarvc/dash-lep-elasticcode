package com.br.agilize.dash.model.entity.dashboardEntity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "pr_from_github")
public class PrFromGitHubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "pr_count")
    private Integer prCount;

    @ElementCollection
    @CollectionTable(name = "pr_dates", joinColumns = @JoinColumn(name = "pull_request_id"))
    @Column(name = "date")
    private List<String> prDates;

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCount() {
        return prCount;
    }

    public void setCount(int count) {
        this.prCount = count;
    }

    public List<String> getDates() {
        return prDates;
    }

    public void setDates(List<String> dates) {
        this.prDates = dates;
    }
}
