package com.example.project.controller;

import com.example.project.model.entity.User;
import com.example.project.repository.UserRepository;
import com.example.project.model.entity.User;
import com.example.project.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@Controller
@RequestMapping("/user")
public class Test {


    @Autowired
    private UserRepository userRepository;

    @PostMapping("/join.do")
    public String create(User user) {

        userRepository.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/signUp")
    public String user(){
        return "signUp";
    }



//    private final FaqService faqService;
//
//    private final FileService fileService;
//
//    @GetMapping("/post")
//    public String post(){
//        return "board/post";
//    }
//
//    @PostMapping("/post")
//    public String readtest(@RequestParam("file") MultipartFile files, FaqDTO faqDTO) {
//        try {
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
//            String savePath = System.getProperty("user.dir") + "\\files";
//            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdir();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//            String filePath = savePath + "\\" + filename;
//            files.transferTo(new File(filePath));
//
//            FileDTO fileDto = new FileDTO();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = fileService.saveFile(fileDto);
//            faqDTO.setFileId(fileId);
//            faqService.savePost(faqDTO);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return "redirect:/home";
//    }
}
