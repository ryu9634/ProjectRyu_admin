package com.example.project.model.DTO;

import com.example.project.model.entity.Goods;
import com.example.project.model.enumclass.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long orderIdx;
    private Long userIdx;
    private String orderNum;
    private LocalDateTime orderRegdate;
    private String orderSeller;
    private OrderStatus orderStatus;
    private Goods goods;
}
