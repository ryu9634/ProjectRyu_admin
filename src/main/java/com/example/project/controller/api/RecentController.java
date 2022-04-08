package com.example.project.controller.api;

import com.example.project.model.DTO.RecentDTO;
import com.example.project.model.entity.Recent;
import com.example.project.service.RecentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recent")
@RequiredArgsConstructor
public class RecentController {

    private final RecentService recentService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody RecentDTO recentDTO){
        recentService.create(userIdx, recentDTO);
    }

    @GetMapping("/list/{userIdx}")
    public List<RecentDTO> list(@PathVariable Long userIdx){
        List<RecentDTO> recentDTOList = recentService.getRecentList(userIdx);
        return recentDTOList;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        recentService.delete(id);
    }

    @DeleteMapping("/deleteAll/{userIdx}")
    public void deleteAll(@PathVariable Long userIdx) {
        recentService.deleteAll(userIdx);
    }


}
