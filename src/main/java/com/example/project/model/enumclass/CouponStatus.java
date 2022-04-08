package com.example.project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponStatus {
    REGISTERED(0, "사용가능", "쿠폰 사용가능 상태"),
    UNREGISTERED(1, "소멸됨", "쿠폰 사용불가 상태");

    private Integer id;
    private String title;
    private String description;
}
