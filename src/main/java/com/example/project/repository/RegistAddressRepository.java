package com.example.project.repository;

import com.example.project.model.entity.RegistAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistAddressRepository extends JpaRepository<RegistAddress, Long> {
    List<RegistAddress> findAllByUserIdx(Long userIdx);
}
