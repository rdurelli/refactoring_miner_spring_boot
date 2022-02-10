package com.example.refactoring_miner_projects.repository;

import com.example.refactoring_miner_projects.model.BriefBetweenTagsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BriefBetweenTagsRepository extends JpaRepository<BriefBetweenTagsModel, Integer> {
}
