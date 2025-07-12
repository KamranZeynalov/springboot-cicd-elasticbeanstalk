package com.company.cloudapp.model.integration;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntegrationCreationRequest {
    @NotNull
    @NotBlank
    private String phoneNumber;

}
