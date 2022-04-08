package com.example.project.model.DTO;

import com.example.project.model.enumclass.AdminStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AdminDTO {
    private Long adminIdx;
    private String adminId;
    private String adminPw;
    private String adminName;
//    private AdminStatus adminStatus;
}
