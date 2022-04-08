package com.example.project.model.entity;

import com.example.project.model.enumclass.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name = "seq_coupon",
        sequenceName = "seq_coupon",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_coupon")
    private Long cpIdx;
    private Long userIdx;
    private BigDecimal cpDiscount;
    private String cpName;
    @CreatedDate
    private LocalDateTime cpRegdate;
    @CreatedDate
    private LocalDateTime cpStartdate;
    private LocalDateTime cpEnddate;
    private String cpLimitprice;
    @Enumerated(EnumType.STRING)
    private CouponStatus cpStatus;
}
