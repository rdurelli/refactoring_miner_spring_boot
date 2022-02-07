package com.example.refactoring_miner_projects.repository;

import com.example.refactoring_miner_projects.model.RefactoringModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefactoringRepository extends JpaRepository<RefactoringModel, Integer> {

}
