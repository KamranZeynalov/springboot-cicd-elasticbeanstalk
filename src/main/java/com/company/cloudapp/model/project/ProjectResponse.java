package com.company.cloudapp.model.project;

import com.company.cloudapp.entity.Project;
import com.company.cloudapp.entity.User;
import com.company.cloudapp.model.ip.IpResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private List<Long> userIds;
    private List<IpResponse> ipList;
    private Long ipCount;

    public ProjectResponse(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.userIds = project.getUsers().stream().map(User::getId).toList();
        this.ipCount = project.getIpCount();
    }
}
