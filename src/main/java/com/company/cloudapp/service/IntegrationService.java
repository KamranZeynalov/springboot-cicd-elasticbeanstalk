package com.company.cloudapp.service;

import com.company.cloudapp.constant.IntegrationStatus;
import com.company.cloudapp.entity.Integration;
import com.company.cloudapp.exception.IntegrationNotFoundException;
import com.company.cloudapp.model.integration.IntegrationCreationRequest;
import com.company.cloudapp.model.integration.IntegrationResponse;
import com.company.cloudapp.model.integration.IntegrationStatusRequest;
import com.company.cloudapp.repository.IntegrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntegrationService {

    private final IntegrationRepository integrationRepository;

    public List<IntegrationResponse> getAllIntegrations() {
        return integrationRepository.findAll().stream().map(IntegrationResponse::new).toList();
    }

    public Integration findById(Long id) {
        log.info("Fetching integration by id: {}", id);
        return integrationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Integration with id {} not found", id);
                    return new IntegrationNotFoundException("Integration not found");
                });

    }

    public Long createIntegration(IntegrationCreationRequest request) {
        log.info("Creating integration: {}", request);
        Integration integration = Integration.builder()
                                             .phoneNumber(request.getPhoneNumber())
                                             .status(IntegrationStatus.WAITING)
                                             .build();

        return integrationRepository.save(integration).getId();

        //TODO the manager should get a notification about the status of the integration
    }

    public Long updateIntegrationState(Long id, IntegrationStatusRequest request) {
        log.info("Updating integration state with id: {}", id);
        Integration integration = findById(id);

        if (request.isAccepted()) {
            if (integration.getStatus().equals(IntegrationStatus.WAITING)) {
                integration.setStatus(IntegrationStatus.IN_PROGRESS);
            } else {
                integration.setStatus(IntegrationStatus.APPROVED);
            }
        } else {
            integration.setStatus(IntegrationStatus.REJECTED);
        }

        //TODO the customer should get a notification about the status of the integration

        return integrationRepository.save(integration).getId();
    }


    public void deleteIntegration(Long id) {
        log.info("Deleting integration by id: {}", id);
        Integration integration = findById(id);
        integration.setDeletedAt(LocalDateTime.now());
        integrationRepository.save(integration);
    }
}
