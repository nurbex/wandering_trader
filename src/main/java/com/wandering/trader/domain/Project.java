package com.wandering.trader.domain;


import javax.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectName;

    private String projectDescription;

    @OneToOne
    private Image projectImage;

    public Project (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Image getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(Image projectImage) {
        this.projectImage = projectImage;
    }
}
