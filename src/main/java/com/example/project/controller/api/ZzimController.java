package com.example.project.controller.api;

import com.example.project.model.DTO.ZzimDTO;
import com.example.project.service.ZzimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/zzim")
public class ZzimController {

    private final ZzimService zzimService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody ZzimDTO zzimDTO){
        zzimService.create(userIdx, zzimDTO);
    }


    @GetMapping("/list/{userIdx}")
    public List<ZzimDTO> list(@PathVariable Long userIdx){
        List<ZzimDTO> zzimDTOList = zzimService.getZzimList(userIdx);
        return zzimDTOList;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        zzimService.delete(id);
    }

    @DeleteMapping("/deleteAll/{userIdx}")
    public void deleteAll(@PathVariable Long userIdx){
        zzimService.deleteAll(userIdx);
    }
}
