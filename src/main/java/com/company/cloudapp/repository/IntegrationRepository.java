package com.company.cloudapp.repository;

import com.company.cloudapp.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IntegrationRepository extends JpaRepository<Integration,Long> {
}
