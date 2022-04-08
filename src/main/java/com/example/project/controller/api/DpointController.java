package com.example.project.controller.api;

import com.example.project.model.DTO.DpointDTO;
import com.example.project.service.DpointService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dpoint")
@RequiredArgsConstructor
public class DpointController {
/*
    {
      "dpContent":"무료",
      "dpPp":1500,
      "dpMp":100
    }
 */
    private final DpointService dpointService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody DpointDTO dpointDTO){
        dpointService.create(userIdx, dpointDTO);
    }

    @GetMapping("{id}")
    public DpointDTO read(@PathVariable Long id){
        return dpointService.read(id);
    }

    @GetMapping("/list/{userIdx}")
    public List<DpointDTO> list(@PathVariable Long userIdx){
        List<DpointDTO> dpointDTOList =  dpointService.getDpointList(userIdx);
        return dpointDTOList;
    }


    @PostMapping("/update/{dpIdx}")
    public void update(@PathVariable Long dpIdx, @RequestBody DpointDTO dpointDTO){
        dpointService.update(dpIdx, dpointDTO);
    }

    @PostMapping("/delete/{dpIdx}")
    public void delete(@PathVariable Long dpIdx){
        dpointService.delete(dpIdx);
    }
}
