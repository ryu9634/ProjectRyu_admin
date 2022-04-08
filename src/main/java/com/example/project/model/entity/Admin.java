package com.example.project.model.entity;

import com.example.project.model.enumclass.AdminStatus;
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
        name = "seq_admin",
        sequenceName = "seq_admin",
        initialValue = 1,
        allocationSize = 1

)
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name="tb_admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_admin")
    private Long adminIdx;
    private String adminId;
    private String adminPw;
    private String adminName;
//    @Enumerated(EnumType.STRING)
//    private AdminStatus adminStatus;
}
