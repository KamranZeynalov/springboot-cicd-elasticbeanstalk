package com.company.cloudapp.model.integration;

import com.company.cloudapp.constant.IntegrationStatus;
import com.company.cloudapp.entity.Integration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationResponse {
    private Long id;
    private IntegrationStatus status;
    private Long acceptedBy;
    private LocalDateTime createdAt;

    public IntegrationResponse(Integration integration) {
        this.id = integration.getId();
        this.status = integration.getStatus();
        this.acceptedBy = integration.getAcceptedBy().getId();
        this.createdAt = integration.getCreatedAt();
    }
}
