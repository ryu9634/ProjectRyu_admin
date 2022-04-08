package com.example.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SequenceGenerator(
        name="seq_faq",
        sequenceName = "seq_faq",
        initialValue = 1,
        allocationSize = 1
)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_faq")
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_faq")
    private Long faqIdx;
    private String faqTitle;
    private String faqAnswer;
    private String faqCategory;
    private String faqImg;
}
