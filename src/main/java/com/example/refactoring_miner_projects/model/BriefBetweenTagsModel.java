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
@Entity(name = "brief_between_tags")
public class BriefBetweenTagsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_name")
    private String projectName;
    @Column(name = "tag_source")
    private String tagSource;
    @Column(name = "tag_target")
    private String tagTarget;
    @Column(name = "refactoring_between_tags")
    private Integer refactoringBetweenTags;
    @Column(name = "commit_between_tags")
    private Integer commitBetweenTags;
    @Column(name = "errors_between_tags")
    private Integer errorsBetweenTags;
}
