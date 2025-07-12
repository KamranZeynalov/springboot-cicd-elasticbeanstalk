package com.company.cloudapp.service;

import com.company.cloudapp.entity.Contract;
import com.company.cloudapp.exception.ContractNotFoundException;
import com.company.cloudapp.model.contract.ContractCreationRequest;
import com.company.cloudapp.model.contract.ContractFilter;
import com.company.cloudapp.model.contract.ContractResponse;
import com.company.cloudapp.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;


    public List<ContractResponse> getAllContracts(ContractFilter filter) {
        return null;
    }

    public Contract findContractById(Long id) {
        log.info("Fetching contract by id: {}", id);
        return contractRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Contract with id {} not found", id);
                    return new ContractNotFoundException("Contract not found");
                });

    }

    public Contract createContract(ContractCreationRequest request) {

        Contract contract = Contract.builder()
                                    .status(request.getStatus())
                                    .type(request.getType())
                                    .paymentStatus(request.getPaymentStatus())
                                    .validityPeriod(request.getValidityPeriod())
                                    .documentId(request.getDocumentId())
                    //TODO set caller(user) id  .createdBy(getCurrentUserId())
                                    .build();
        contractRepository.save(contract);

        // TODO send notification to manager
        return null;
    }

    public void deleteContract(Long id) {
        log.info("Deleting contract by id: {}", id);
        Contract contract = findContractById(id);
        contract.setDeletedAt(LocalDateTime.now());
        contractRepository.save(contract);
    }
}
