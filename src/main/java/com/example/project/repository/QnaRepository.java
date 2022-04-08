package com.example.project.repository;

import com.example.project.model.entity.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
    List<Qna> findAllByUserIdx(Long userIdx);
}
