package com.example.project.model.entity;

import com.example.project.model.enumclass.GoodsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_goods",
        sequenceName = "seq_goods",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_goods")
    private Long gdIdx;
    private String gdName;
    private BigDecimal gdPrice;
    private Long gdCount;
    private String gdBrand;
    @CreatedDate
    private LocalDateTime gdRegdate;
    private String gdCategory;
    private BigDecimal gdSaleprice;
    private BigDecimal gdSalepercent;
    private Integer gdHit;
    private String gdImg;
    private String gdOption;
    private String gdContent;
    private String gdDetailimg;
    @Enumerated(EnumType.STRING)
    private GoodsStatus gdStatus;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<Cart> cartList;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<Order> orderList;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<Recent> recentList;

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    private List<Zzim> zzimList;
}
