package com.company.cloudapp.repository;

import com.company.cloudapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByPin(String personalCode);

}
