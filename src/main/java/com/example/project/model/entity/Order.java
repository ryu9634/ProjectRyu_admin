package com.example.project.model.entity;

import com.example.project.model.enumclass.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_order",
        sequenceName = "seq_order",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    private Long orderIdx;
    private Long userIdx;
    private String orderNum;
    private LocalDateTime orderRegdate;
    private String orderSeller;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="gd_idx")
    private Goods goods;
}
