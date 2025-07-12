package com.company.cloudapp.repository;

import com.company.cloudapp.entity.Project;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @EntityGraph(value = "Project.users")
    Project findProjectWithUsersById(Long id);

    Project findByDeletedAtIsNull();

    List<Project> findAllByDeletedAtIsNull(Pageable pageable);

}
