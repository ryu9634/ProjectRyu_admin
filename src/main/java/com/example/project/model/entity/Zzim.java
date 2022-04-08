package com.example.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_zzim",
        sequenceName = "seq_zzim",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_zzim")
public class Zzim {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_zzim")
    private Long zzIdx;
    private Long userIdx;

    @ManyToOne
    @JoinColumn(name="gd_idx")
    private Goods goods;
}
