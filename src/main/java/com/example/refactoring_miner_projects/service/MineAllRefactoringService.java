package com.example.refactoring_miner_projects.service;

import com.example.refactoring_miner_projects.model.RefactoringModel;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MineAllRefactoringService {
    private final RefactoringService refactoringService;
    public void mine() throws Exception {
        GitService gitService = new GitServiceImpl();
        GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

        Repository repo = gitService.cloneIfNotExists(
                "tmp/maven",
                "https://github.com/apache/maven.git");

        miner.detectAll(repo, "master", new RefactoringHandler() {
            @Override
            public void handle(String commitId, List<Refactoring> refactorings) {
                System.out.println("Refactorings at " + commitId);
                for (Refactoring ref : refactorings) {
                    System.out.println(ref.toString());
                    RefactoringModel refactoringModel = RefactoringModel.builder()
                            .name(ref.getName())
                            .build();
                    refactoringService.save(refactoringModel);
                }
            }
        });
    }

}
