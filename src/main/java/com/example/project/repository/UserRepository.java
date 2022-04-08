package com.example.project.repository;

import com.example.project.model.entity.User;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserUserid(String userUserid);
    Optional<User> findByUserIdx(Long userIdx);
}
