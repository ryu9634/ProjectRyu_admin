package com.example.project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoodsStatus {
    SELL(0, "판매중", "상품 구매가능 상태"),
    SOLDOUT(1, "재고없음", "상품 구매불가 상태");

    private Integer id;
    private String title;
    private String description;
}
