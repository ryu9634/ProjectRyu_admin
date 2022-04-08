package com.example.project.model.DTO;


import com.example.project.model.entity.Notice;
import com.example.project.repository.NoticeRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class NoticeDTO {
    private Long ntIdx;
    private String ntTitle;
    private String ntContent;
    private String ntCategory;
    private LocalDateTime ntRegdate;
    private Integer ntHit;
}
