package com.example.project.service;

import com.example.project.model.entity.Admin;
import com.example.project.repository.AdminRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AdminLoginService {

    AdminRepository adminRepository;


    public boolean login(String adminId, String adminPw) {
        Admin admin = adminRepository.findByAdminId(adminId);
        log.info(adminId + "  -  " + adminPw);
        log.info(admin+ " ");
        if (admin != null && admin.getAdminPw().equals(adminPw)) {
            log.info(adminId + "  -  " + adminPw);
            return true;
        } else {

        log.info("실패");
        return false; }
    }

}

