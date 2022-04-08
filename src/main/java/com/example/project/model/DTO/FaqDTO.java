package com.example.project.model.DTO;

import com.example.project.model.entity.Faq;
import com.example.project.repository.FaqRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class FaqDTO {
    private Long faqIdx;
    private String faqTitle;
    private String faqAnswer;
    private String faqCategory;
    private String faqImg;
}
