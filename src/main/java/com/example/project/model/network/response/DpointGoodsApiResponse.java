package com.example.project.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DpointGoodsApiResponse {
    private Long dpgIdx;
    private String dpgName;
    private Integer dpgPrice;
    private Integer dpgCount;
    private LocalDateTime dpgEnddate;
    private String dpgCategory;
    private Integer dpgHit;
    private String dpgImg;
}
