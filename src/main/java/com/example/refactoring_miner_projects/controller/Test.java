package com.example.refactoring_miner_projects.controller;

import com.example.refactoring_miner_projects.service.RefactoringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.refactoring_miner_projects.service.MineAllRefactoringService;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {
    private final MineAllRefactoringService mineAllRefactoringService;
    private final RefactoringService refactoringService;

    @GetMapping
    public void execute() throws Exception {
        mineAllRefactoringService.mine();
    }

    @GetMapping("/countAllByRefactoringType/{refactoringType}")
    public Long countAllByRefactoringType(@PathVariable String refactoringType) {
        return refactoringService.countAllByRefactoringType(refactoringType);
    }

    @GetMapping("/countAllByTagSourceAndTagTarget/{tagSource}/{tagTarget}")
    public Long countAllByTagSourceAndTagTarget(@PathVariable String tagSource, @PathVariable String tagTarget) {
        return refactoringService.countAllByTagSourceAndTagTarget(tagSource, tagTarget);
    }

}
