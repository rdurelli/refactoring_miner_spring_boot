package com.example.refactoring_miner_projects.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.refactoring_miner_projects.service.MineAllRefactoringService;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {
    private final MineAllRefactoringService refactoringService;

    @GetMapping
    public void execute() throws Exception {
        refactoringService.mine();
    }

}
