package com.example.project.model.DTO;

import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Recent;
import com.example.project.model.entity.User;
import com.example.project.repository.RecentRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecentDTO {
    private Long rcIdx;
    private Long rcUserIdx;
    private Goods goods;
}
