package com.example.project.model.DTO;

import com.example.project.model.entity.Qna;
import com.example.project.model.entity.User;
import com.example.project.model.enumclass.QnaStatus;
import com.example.project.repository.FaqRepository;
import com.example.project.repository.QnaRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QnaDTO {
    private Long qIdx;
    private Long userIdx;
    private String qTitle;
    private LocalDateTime qRegdate;
    private String qContent;
    private String qAnswer;
    private Integer qNumber;
    private String qCategory;
    private QnaStatus qStatus;
}
