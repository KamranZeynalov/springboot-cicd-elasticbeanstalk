package com.company.cloudapp.model.integration;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntegrationStatusRequest {
    private boolean isAccepted;
}
