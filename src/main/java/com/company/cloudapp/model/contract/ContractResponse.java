package com.company.cloudapp.model.contract;

import com.company.cloudapp.constant.ContractPaymentStatus;
import com.company.cloudapp.constant.ContractStatus;
import com.company.cloudapp.constant.ContractType;
import com.company.cloudapp.constant.ContractValidityPeriod;
import com.company.cloudapp.entity.Contract;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
    private Long id;
    private ContractStatus status;
    private ContractType type;
    private ContractPaymentStatus paymentStatus;
    private ContractValidityPeriod validityPeriod;
    private Long createdBy;
    private Long signedBy;
    private LocalDateTime signedAt;

    public ContractResponse(Contract contract) {
        this.id = contract.getId();
        this.status = contract.getStatus();
        this.type = contract.getType();
        this.paymentStatus = contract.getPaymentStatus();
        this.validityPeriod = contract.getValidityPeriod();
        this.createdBy = contract.getCreatedBy().getId();
        this.signedBy = contract.getSignedBy().getId();
        this.signedAt = contract.getSignedAt();
    }
}
