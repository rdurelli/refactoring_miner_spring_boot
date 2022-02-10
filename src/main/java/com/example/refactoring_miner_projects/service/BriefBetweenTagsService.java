package com.example.refactoring_miner_projects.service;

import com.example.refactoring_miner_projects.model.BriefBetweenTagsModel;
import com.example.refactoring_miner_projects.repository.BriefBetweenTagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BriefBetweenTagsService {
    private final BriefBetweenTagsRepository briefBetweenTagsRepository;

    public BriefBetweenTagsModel save(BriefBetweenTagsModel briefBetweenTagsModel) {
        return this.briefBetweenTagsRepository.save(briefBetweenTagsModel);
    }
}
