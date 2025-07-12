package com.company.cloudapp.service;

import com.company.cloudapp.entity.Project;
import com.company.cloudapp.exception.ProjectNotFoundException;
import com.company.cloudapp.model.project.ProjectCreationRequest;
import com.company.cloudapp.model.project.ProjectResponse;
import com.company.cloudapp.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;


    public List<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable).getContent().stream().map(ProjectResponse::new).toList();
    }

    public Project findProjectById(Long id) {
        log.info("Fetching project by id: {}", id);
        return projectRepository.findById(id)
                                .orElseThrow(() -> {
            log.error("Project with id {} not found", id);
            return new ProjectNotFoundException("Project not found");
        });

    }

    public Long createProject(ProjectCreationRequest request) {
        log.info("Creating new project by name: {}", request.getName());
        Project project = Project.builder()
                                 .name(request.getName())
                                 // TODO create a method for getting caller id
                                 //.user(getCurrentUser)
                                 .description(request.getDescription())
                                 .build();
        return projectRepository.save(project).getId();
    }

    public Project findByIdWithUsers(Long projectId) {
        return projectRepository.findProjectWithUsersById(projectId);
    }

    public void deleteProject(Long id) {
        log.info("Deleting project by id: {}", id);
        Project project = findProjectById(id);
        project.setDeletedAt(LocalDateTime.now());
        projectRepository.save(project);
    }
}
