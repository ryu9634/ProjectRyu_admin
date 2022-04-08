package com.example.project.repository;

import com.example.project.model.entity.Goods;
import com.example.project.model.enumclass.GoodsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> findAllByGdBrand(String gdBrand);
    List<Goods> findAllByGdCategory(String gdCategory);
}
