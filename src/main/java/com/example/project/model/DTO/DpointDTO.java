package com.example.project.model.DTO;

import com.example.project.model.entity.Dpoint;
import com.example.project.model.entity.User;
import com.example.project.repository.DpointRepository;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class DpointDTO {
    private Long dpIdx;
    private LocalDateTime dpRegdate;
    private LocalDateTime dpEnddate;
    private String dpContent;
    private BigDecimal dpPp;
    private BigDecimal dpMp;
    private BigDecimal dpSum;
    private Long userIdx;
}
