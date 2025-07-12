package com.company.cloudapp.controller;

import com.company.cloudapp.exception.ErrorResponse;
import com.company.cloudapp.model.project.ProjectCreationRequest;
import com.company.cloudapp.model.project.ProjectResponse;
import com.company.cloudapp.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Project")
@RequiredArgsConstructor
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Operation(summary = "Get all projects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Projects fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProjectResponse.class))) })})
    @GetMapping("api/v1/project")
    public ResponseEntity<List<ProjectResponse>> getAllProjects(@Parameter @RequestParam(defaultValue = "0") int page,
                                                                @Parameter @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(projectService.getAllProjects(PageRequest.of(page,size)));
    }

    @Operation(summary = "Get project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project fetched successfully",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = ProjectResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("api/v1/project/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@Parameter(description = "ID of the project to be fetched")
                                                          @PathVariable Long id) {
        return ResponseEntity.ok(new ProjectResponse(projectService.findProjectById(id)));
    }


    @Operation(summary = "Create new project")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Project created successfully",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = Long.class, description = "The ID of the new project"))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("api/v1/project")
    public ResponseEntity<Long> createProject(@RequestBody @Valid ProjectCreationRequest request) {
        return new ResponseEntity<>(projectService.createProject(request), HttpStatus.CREATED);
    }


    @Operation(summary = "Delete project by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Project deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",
                                       schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping("api/v1/project/{id}")
    public ResponseEntity<Void> deleteProjectById(@Parameter(description = "ID of the project to be fetched")
                                                  @PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

}
