package com.example.refactoring_miner_projects.service;

import com.example.refactoring_miner_projects.model.BriefBetweenTagsModel;
import com.example.refactoring_miner_projects.model.RefactoringModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.lib.Repository;
import org.refactoringminer.api.GitHistoryRefactoringMiner;
import org.refactoringminer.api.GitService;
import org.refactoringminer.api.Refactoring;
import org.refactoringminer.api.RefactoringHandler;
import org.refactoringminer.rm1.GitHistoryRefactoringMinerImpl;
import org.refactoringminer.util.GitServiceImpl;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MineAllRefactoringService {

    private final ResourceLoader resourceLoader;
    private final RefactoringService refactoringService;
    private final BriefBetweenTagsService briefBetweenTagsService;

    public void mine() throws Exception {
        List<String> allTags = readCsvFile();
        GitService gitService = new GitServiceImpl();
        GitHistoryRefactoringMiner miner = new GitHistoryRefactoringMinerImpl();

        String project = "maven";
        String gitRepoURL = "https://github.com/apache/maven.git";
        Repository repo = gitService.cloneIfNotExists(
                "tmp/" + project,
                gitRepoURL);

        for (int i = 0; i < allTags.size(); i++) {
            if (i + 1 < allTags.size()) {
                String tagSource = allTags.get(i).trim();
                String tagTarget = allTags.get(i + 1).trim();
                miner.detectBetweenTags(repo, tagSource, tagTarget, new RefactoringHandler() {
                    @Override
                    public void onFinish(int refactoringsCount, int commitsCount, int errorCommitsCount) {
                        log.info("Refactoring Count: " + refactoringsCount);
                        log.info("Commits Count: " + commitsCount);
                        log.info("Errors commits Count: " + errorCommitsCount);
                        BriefBetweenTagsModel briefBetweenTagsModel = BriefBetweenTagsModel.builder()
                                .projectName(project)
                                .tagSource(tagSource)
                                .tagTarget(tagTarget)
                                .refactoringBetweenTags(refactoringsCount)
                                .commitBetweenTags(commitsCount)
                                .errorsBetweenTags(errorCommitsCount)
                                .build();
                        log.info("Saving" + briefBetweenTagsModel);
                        briefBetweenTagsService.save(briefBetweenTagsModel);
                    }

                    @Override
                    public void handle(String commitId, List<Refactoring> refactorings) {
                        log.info("Refactorings at " + commitId);
                        for (Refactoring ref : refactorings) {
                            RefactoringModel refactoringModel = RefactoringModel.builder()
                                    .name(ref.getName())
                                    .refactoringType(ref.getRefactoringType().getDisplayName())
                                    .jsonRepresentation(ref.toJSON())
                                    .project(project)
                                    .projectWithTag(project)
                                    .projectURL(gitRepoURL)
                                    .tagSource(tagSource)
                                    .tagTarget(tagTarget)
                                    .commitID(commitId)
                                    .build();
                            log.info("Saving" + refactoringModel);
                            refactoringService.save(refactoringModel);
                        }
                    }
                });
            }
        }
    }

    private List<String> readCsvFile() throws IOException {
        List<String> tags = new ArrayList();
        Resource resource = resourceLoader.getResource("classpath:repository_tag.csv");
        InputStream inputStream = resource.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            tags.add(line);
        }
        //remove csv HEAD
        tags.remove(0);
        return tags;
    }

}
