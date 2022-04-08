package com.example.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_dpoint_goods",
        sequenceName = "seq_dpoint_goods",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_dpoint_goods")
public class DpointGoods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dpoint_goods")
    private Long dpgIdx;
    private String dpgName;
    private Integer dpgPrice;
    private Integer dpgCount;
    private LocalDateTime dpgEnddate;
    private String dpgCategory;
    private Integer dpgHit;
    private String dpgImg;
}
