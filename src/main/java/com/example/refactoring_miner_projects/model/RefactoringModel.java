package com.example.refactoring_miner_projects.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "refactorings")
public class RefactoringModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "refactoring_type")
    private String refactoringType;

    @Column(name = "json_representation")
    private String jsonRepresentation;

    private String project;

    @Column(name = "project_with_tag")
    private String projectWithTag;

    @Column(name = "project_url")
    private String projectURL;

    @Column(name = "commit_id")
    private String commitID;

    @Column(name = "tag_source")
    private String tagSource;

    @Column(name = "tag_target")
    private String tagTarget;

}
