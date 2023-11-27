package com.projectss.theUltimateTodo.OAuth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String userId);

    Boolean existsUserByOpenId(String openId);

    Optional<User> findUserByOpenId(String openId);

    Optional<User> findUserByUserEmail(String email);
}
