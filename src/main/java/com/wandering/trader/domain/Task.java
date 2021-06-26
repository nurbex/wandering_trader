package com.wandering.trader.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    private String todo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum TYPE {
        IDEA,
        BUG,
        ISSUE,
        FEATURE,
        ENHANCEMENT,
    }

    public enum STATUS {
        TODO,
        THISWEEK,
        TODAY,
        DONE
    };

    private TYPE type;

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

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
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

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
