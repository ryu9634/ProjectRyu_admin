package com.example.project.controller.api;

import com.example.project.model.DTO.AdminDTO;
import com.example.project.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin2")
public class AdminController {

    @Autowired
    private final AdminService adminService;

    @PostMapping("/new")
    public void create(@RequestBody AdminDTO adminDTO){
        adminService.create(adminDTO);
    }

    @GetMapping("/list")
    public List<AdminDTO> list(){
        List<AdminDTO> adminDTOList = adminService.getAdminList();
        return adminDTOList;
    }

//    @PutMapping("")
//    public void update(@RequestBody AdminDTO adminDTO){
//        adminService.update(adminDTO);
//    }
}
