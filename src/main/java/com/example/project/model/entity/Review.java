package com.example.project.model.entity;

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
        name="seq_review",
        sequenceName = "seq_review",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_review")
    private Long rvIdx;
    private Long userIdx;
    private LocalDateTime rvRegdate;
    private String rvTitle;
    private String rvContent;
    private Integer rvStar;
    private String rvImg;
}
