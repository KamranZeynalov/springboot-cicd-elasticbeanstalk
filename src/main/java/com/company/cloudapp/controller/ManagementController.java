package com.company.cloudapp.controller;

import com.company.cloudapp.exception.ErrorResponse;
import com.company.cloudapp.model.integration.IntegrationStatusRequest;
import com.company.cloudapp.service.IntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Manager operations")
@RestController
@RequiredArgsConstructor
public class ManagementController {

    private final IntegrationService integrationService;

    @Operation(summary = "Accept or reject the integration",
            description = "This API allows the manager to either accept or reject an integration request. The manager's decision will trigger the next steps in the integration process.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Integration state updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class, description = "The ID of the integration")) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - Manager role required",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @PutMapping("api/v1/integration/{id}")
    public ResponseEntity<Long> updateIntegrationState(@Parameter(description = "ID of the integration to be either accepted or rejected")
                                                       @PathVariable Long id, @RequestBody @Valid IntegrationStatusRequest request) {
        return ResponseEntity.ok(integrationService.updateIntegrationState(id,request));
    }
}
