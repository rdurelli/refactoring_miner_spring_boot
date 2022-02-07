package com.example.refactoring_miner_projects.service;

import com.example.refactoring_miner_projects.model.RefactoringModel;
import com.example.refactoring_miner_projects.repository.RefactoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefactoringService {
    private final RefactoringRepository refactoringRepository;

    public RefactoringModel save(RefactoringModel refactoringModel) {
        return this.refactoringRepository.save(refactoringModel);
    }
}
