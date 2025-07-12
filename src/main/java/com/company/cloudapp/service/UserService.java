package com.company.cloudapp.service;

import com.company.cloudapp.entity.User;
import com.company.cloudapp.exception.UserNotFoundException;
import com.company.cloudapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findUserById(Long id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User with id {} not found", id);
                    return new UserNotFoundException("User not found");
                });

    }

    public User findUserByPersonalCode(String personalCode) {
        log.info("Getting user by personal code: {}", personalCode);

        return Optional.ofNullable(userRepository.findByPin(personalCode))
                .orElseThrow(() -> {
                    log.error("User with personal code {} not found", personalCode);
                    return new UserNotFoundException("User not found");
                });
    }

    public User saveUser(User user) {
        log.info("Saving user by phone number: {}", user.getPhoneNumber());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        log.info("Deleting user by id: {}", id);
        User user = findUserById(id);
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
    }
}
