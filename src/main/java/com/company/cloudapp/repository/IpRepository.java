package com.company.cloudapp.repository;

import com.company.cloudapp.entity.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IpRepository extends JpaRepository<Ip,Long> {

}
