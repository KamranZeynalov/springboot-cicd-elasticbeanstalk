package com.company.cloudapp.model.contract;


import com.company.cloudapp.constant.ContractPaymentStatus;
import com.company.cloudapp.constant.ContractStatus;
import com.company.cloudapp.constant.ContractType;
import com.company.cloudapp.constant.ContractValidityPeriod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractCreationRequest {
    private ContractStatus status;
    private ContractType type;
    private ContractPaymentStatus paymentStatus;
    private ContractValidityPeriod validityPeriod;
    @NotNull
    @NotBlank
    private String documentId;

}
