package com.example.project.model.DTO;

import com.example.project.model.enumclass.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CouponDTO {
    private Long cpIdx;
    private Long userIdx;
    private BigDecimal cpDiscount;
    private String cpName;
    private LocalDateTime cpRegdate;
    private LocalDateTime cpStartdate;
    private LocalDateTime cpEnddate;
    private String cpLimitprice;
    private CouponStatus cpStatus;

}
