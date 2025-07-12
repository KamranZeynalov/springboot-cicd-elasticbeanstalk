package com.company.cloudapp.model.ip;

import com.company.cloudapp.constant.Environment;
import com.company.cloudapp.entity.Ip;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IpResponse {
    private Long id;
    private String serviceName;
    private Long projectId;
    private String description;
    private Environment env;
    private Long createdBy;

    public IpResponse(Ip ip) {
        this.id = ip.getId();
        this.serviceName = ip.getServiceName();
        this.description = ip.getDescription();
        this.projectId = ip.getProject().getId();
        this.env = ip.getEnv();
        this.createdBy = null;
    }
}
