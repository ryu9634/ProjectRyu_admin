package com.example.project.model.DTO;

import com.example.project.model.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CartDTO {
    private Long cartIdx;
    private Long userIdx;
    private Goods goods;
}
