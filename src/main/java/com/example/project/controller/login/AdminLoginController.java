package com.example.project.controller.login;

import com.example.project.service.AdminLoginService;
import com.example.project.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AdminLoginController {



    private final AdminLoginService adminLoginService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("/admin")
    public String admin() { return "index";}


    @PostMapping("/admin/login")
    public String postAdmin(Model model, @RequestParam (value = "adminId")String adminId, @RequestParam(value = "adminPw") String adminPw){

        log.info("adminId :" + adminId  );
        log.info("adminId :" + adminPw  );
        if(adminLoginService.login(adminId,adminPw)) {
            log.info("adminId :" + adminId  );
            log.info("adminId :" + adminPw  );
            session.setAttribute("adminId", adminId);
            log.info("login 성공");
            model.addAttribute("UserList", userApiLogicService.getUserList());
            return "adminpage/memberList";
        } else{
        return "loginfail"; //로그인 실패 메세지
            }

    }
    //로그인 실패 페이지
    @GetMapping("/loginfail")
    public String duoback_loginfail() {
        return "index";
    }


    @GetMapping("/admin/logout")
    public String logout(){
        session.invalidate();
        return "index";
    }

    @GetMapping({"/memberlist"})
    public String memberlist(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("UserList", this.userApiLogicService.getUserList());
        return "adminpage/memberList";
    }
}
