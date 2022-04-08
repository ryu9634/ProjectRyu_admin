package com.example.project.repository;

import com.example.project.model.entity.Recent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentRepository extends JpaRepository<Recent, Long> {
    List<Recent> findAllByUserIdx(Long userIdx);
    Long deleteAllByUserIdx(Long userIdx);
}
