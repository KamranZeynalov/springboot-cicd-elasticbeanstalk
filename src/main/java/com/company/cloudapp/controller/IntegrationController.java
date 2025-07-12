package com.company.cloudapp.controller;

import com.company.cloudapp.exception.ErrorResponse;
import com.company.cloudapp.model.integration.IntegrationCreationRequest;
import com.company.cloudapp.model.integration.IntegrationResponse;
import com.company.cloudapp.service.IntegrationService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Integration")
@RestController
@RequiredArgsConstructor
public class IntegrationController {

    private final IntegrationService integrationService;


    @Operation(summary = "Get all integration requests")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Integration requests fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = IntegrationResponse.class))) })})
    @GetMapping("api/v1/integration")
    public ResponseEntity<List<IntegrationResponse>> getAllIntegrations() {
        return ResponseEntity.ok(integrationService.getAllIntegrations());
    }

    @Operation(summary = "Get integration by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Integration fetched successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IntegrationResponse.class))),
            @ApiResponse(responseCode = "404", description = "Integration not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("api/v1/integration/{id}")
    public ResponseEntity<IntegrationResponse> getIntegrationById(@Parameter(description = "ID of the integration to be fetched")
                                                @PathVariable Long id) {
        return ResponseEntity.ok(new IntegrationResponse(integrationService.findById(id)));
    }

    @Operation(summary = "Start new integration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Integration started successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class, description = "The ID of the new integration")) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("api/v1/integration")
    public ResponseEntity<Long> createIntegration(@RequestBody @Valid IntegrationCreationRequest request) {
        return new ResponseEntity<>(integrationService.createIntegration(request), HttpStatus.CREATED);
    }
}
