package com.wandering.trader.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String task;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum STATUS {
        IDEA,
        BUG,
        ISSUE,
        FEATURE,
        ENHANCEMENT,
        THISWEEK,
        TODAY,
        TEST,
        DONE
    };

    private STATUS status;

    @OneToOne
    Project project;

    @OneToOne
    CustomUser owner;

    public Task() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public CustomUser getOwner() {
        return owner;
    }

    public void setOwner(CustomUser owner) {
        this.owner = owner;
    }
}
