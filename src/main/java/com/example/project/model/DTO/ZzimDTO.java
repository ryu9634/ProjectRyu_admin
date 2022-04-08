package com.example.project.model.DTO;

import com.example.project.model.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ZzimDTO {
    private Long zzIdx;
    private Long userIdx;
    private Goods goods;
}
