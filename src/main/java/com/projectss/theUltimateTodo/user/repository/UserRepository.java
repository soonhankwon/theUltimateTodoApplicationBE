package com.projectss.theUltimateTodo.user.repository;

import com.projectss.theUltimateTodo.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String userId);

    Boolean existsUserByOpenId(String openId);

    Optional<User> findUserByOpenId(String openId);

    Optional<User> findUserByUserEmail(String email);
}
