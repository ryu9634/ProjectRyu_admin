package com.example.project.model.entity;

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
        name="seq_dpoint",
        sequenceName = "seq_dpoint",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_dpoint")
public class Dpoint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_dpoint")
    private Long dpIdx;
    private LocalDateTime dpRegdate;
    private LocalDateTime dpEnddate;
    private String dpContent;
    private BigDecimal dpPp;
    private BigDecimal dpMp;
    private BigDecimal dpSum;
    private Long userIdx;
}
