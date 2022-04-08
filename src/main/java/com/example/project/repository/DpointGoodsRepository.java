package com.example.project.repository;

import com.example.project.model.entity.DpointGoods;
import com.example.project.model.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DpointGoodsRepository extends JpaRepository<DpointGoods, Long> {
    List<DpointGoods> findAllByDpgCategory(String dpgCategory);
}
