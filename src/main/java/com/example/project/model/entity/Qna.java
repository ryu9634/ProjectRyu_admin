package com.example.project.model.entity;

import com.example.project.model.enumclass.QnaStatus;
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
        name="seq_qna",
        sequenceName = "seq_qna",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_qna")
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_qna")
    private Long qIdx;
    private Long userIdx;
    private String qTitle;
    private LocalDateTime qRegdate;
    private String qContent;
    private String qAnswer;
    private Integer qNumber;
    private String qCategory;

    @Enumerated(EnumType.STRING)
    private QnaStatus qStatus;
}
