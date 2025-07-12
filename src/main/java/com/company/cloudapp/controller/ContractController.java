package com.company.cloudapp.controller;

import com.company.cloudapp.exception.ErrorResponse;
import com.company.cloudapp.model.contract.ContractCreationRequest;
import com.company.cloudapp.model.contract.ContractFilter;
import com.company.cloudapp.model.contract.ContractResponse;
import com.company.cloudapp.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Contract")
@RestController
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @Operation(summary = "Get all contracts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contracts fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ContractResponse.class))) })})
    @GetMapping("api/v1/contract")
    public ResponseEntity<List<ContractResponse>> getAllContracts(@RequestParam(required = false) ContractFilter contractFilter) {
        return ResponseEntity.ok(contractService.getAllContracts(contractFilter));
    }

    @Operation(summary = "Create new contract")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contract created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class, description = "The ID of the new contract")) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("api/v1/contract")
    public ResponseEntity<Long> createContract(@RequestBody @Valid ContractCreationRequest request) {
        return new ResponseEntity<>(contractService.createContract(request).getId(), HttpStatus.CREATED);
    }
}
