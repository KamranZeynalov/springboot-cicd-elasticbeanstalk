package com.company.cloudapp.service;

import com.company.cloudapp.entity.Ip;
import com.company.cloudapp.entity.Project;
import com.company.cloudapp.exception.IpNotFoundException;
import com.company.cloudapp.model.ip.IpCreationRequest;
import com.company.cloudapp.model.ip.IpResponse;
import com.company.cloudapp.model.ip.IpUpdateRequest;
import com.company.cloudapp.repository.IpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class IpService {

    private final IpRepository ipRepository;
    private final ProjectService projectService;

    public List<IpResponse> getAllIps() {
        return ipRepository.findAll().stream().map(IpResponse::new).toList();
    }

    public Ip findIpById(Long id) {
        log.info("Fetching IP by id: {}", id);
        return ipRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("IP with id {} not found", id);
                    return new IpNotFoundException("IP not found");
                });
    }

    public Long createIp(IpCreationRequest request) {
        log.info("Creating new IP by service name: {}", request.getName());
        Project project = projectService.findProjectById(request.getProjectId());
        Ip ip = Ip.builder()
                  .env(request.getEnv())
                  .project(project)
                  //TODO we have to set caller(user) id
//                  .createdBy()
                  .serviceName(request.getName())
                  .build();
        return ipRepository.save(ip).getId();
    }

    public IpResponse updateIp(Long ipId, IpUpdateRequest request) {
        Ip ip = findIpById(ipId);

        log.info("Updating IP by id: {}", ipId);
        ip.setServiceName(request.getName());
        ip.setDescription(request.getDescription());
        ip.setEnv(request.getEnv());

        ipRepository.save(ip);

        return new IpResponse(ip);
    }

    public void deleteIp(Long id) {
        log.info("Deleting IP by id: {}", id);
        Ip ip = findIpById(id);
        ipRepository.delete(ip);
    }
}
