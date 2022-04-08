package com.example.project.model.network.request;

import com.example.project.model.enumclass.GoodsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsApiRequest {
    private Long gdIdx;
    private String gdName;
    private BigDecimal gdPrice;
    private Long gdCount;
    private String gdBrand;
    private LocalDateTime gdRegdate;
    private String gdCategory;
    private BigDecimal gdSaleprice;
    private BigDecimal gdSalepercent;
    private Integer gdHit;
    private String gdImg;
    private String gdOption;
    private String gdDetailimg;
    private String gdContent;
    private GoodsStatus gdStatus;
}
