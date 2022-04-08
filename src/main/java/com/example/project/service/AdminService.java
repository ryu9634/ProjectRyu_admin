package com.example.project.service;

import com.example.project.model.DTO.AdminDTO;
import com.example.project.model.entity.Admin;
import com.example.project.model.enumclass.AdminStatus;
import com.example.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional
    public Admin create(AdminDTO adminDTO){
        Admin admin = Admin.builder()
                .adminId(adminDTO.getAdminId())
                .adminPw(adminDTO.getAdminPw())
                .adminName(adminDTO.getAdminName())
//                .adminStatus(AdminStatus.REGISTERED)
                .build();
        Admin newAdmin = adminRepository.save(admin);
        return newAdmin;
    }

    @Transactional
    public List<AdminDTO> getAdminList(){
        List<Admin> adminList = adminRepository.findAll();
        List<AdminDTO> adminDTOList = new ArrayList<>();

        for(Admin admin : adminList){
            AdminDTO adminDTO = AdminDTO.builder()
                    .adminIdx(admin.getAdminIdx())
                    .adminId(admin.getAdminId())
                    .adminPw(admin.getAdminPw())
                    .adminName(admin.getAdminName())
//                    .adminStatus(admin.getAdminStatus())
                    .build();
            adminDTOList.add(adminDTO);
        }
        return adminDTOList;
    }
//    @Transactional
//    public void update(AdminDTO adminDTO){
//        Optional<Admin> admin = adminRepository.findById(adminDTO.getAdminIdx());
//
//        admin.ifPresent(select -> {
//            select.setAdminStatus(AdminStatus.UNREGISTERED);
//        });
//
//    }

}
