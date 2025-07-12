package com.company.cloudapp.controller;

import com.company.cloudapp.exception.ErrorResponse;
import com.company.cloudapp.model.ip.IpCreationRequest;
import com.company.cloudapp.model.ip.IpResponse;
import com.company.cloudapp.model.ip.IpUpdateRequest;
import com.company.cloudapp.service.IpService;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "IP")
@RestController
@RequiredArgsConstructor
public class IpController {

    private final IpService ipService;

    @Operation(summary = "Get all IPs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IPs fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = IpResponse.class))) })})
    @GetMapping("api/v1/ip")
    public ResponseEntity<List<IpResponse>> getAllIps() {
        return ResponseEntity.ok(ipService.getAllIps());
    }

    @Operation(summary = "Get IP by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IP fetched successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IpResponse.class))),
            @ApiResponse(responseCode = "404", description = "Project not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @GetMapping("api/v1/ip/{id}")
    public ResponseEntity<IpResponse> getIpById(@Parameter(description = "ID of the IP to be fetched")
                                                          @PathVariable Long id) {
        return ResponseEntity.ok(new IpResponse(ipService.findIpById(id)));
    }

    @Operation(summary = "Create new IP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "IP created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Long.class, description = "The ID of the new IP"))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @PostMapping("api/v1/ip")
    public ResponseEntity<Long> createIp(@RequestBody @Valid IpCreationRequest request) {
        return new ResponseEntity<>(ipService.createIp(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Update the IP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IP updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = IpResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @PutMapping("api/v1/ip/{id}")
    public ResponseEntity<IpResponse> updateIp(@Parameter(description = "ID of the IP to be updated")
                                               @PathVariable("id") Long id, @RequestBody @Valid IpUpdateRequest request) {

        return ResponseEntity.ok(ipService.updateIp(id, request));
    }

    @Operation(summary = "Delete IP by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "IP deleted successfully"),
            @ApiResponse(responseCode = "404", description = "IP not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))})
    @DeleteMapping("api/v1/ip/{id}")
    public ResponseEntity<Void> deleteIpById(@Parameter(description = "ID of the IP to be fetched")
                                             @PathVariable Long id) {
        ipService.deleteIp(id);
        return ResponseEntity.ok().build();
    }
}
