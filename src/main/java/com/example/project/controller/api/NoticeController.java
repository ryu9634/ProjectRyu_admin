package com.example.project.controller.api;

import com.example.project.controller.CrudController;
import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.Notice;
import com.example.project.model.network.Header;
import com.example.project.service.NoticeService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notice")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    //http://localhost:8080/notice/new
    @PostMapping("/new")
    public void create(@RequestBody  NoticeDTO noticeDTO)  {

        noticeService.create(noticeDTO);

    }

    @PostMapping("/update/{ntIdx}")
    public void update(@PathVariable Long ntIdx, @RequestBody NoticeDTO noticeDTO){
        noticeService.update(ntIdx, noticeDTO);
    }

    // 공지사항 목록
    @GetMapping("/list")
    public String list(Model model){
        List<NoticeDTO> noticeDTOList = noticeService.getBoardList();
        model.addAttribute("noticeDTOList",noticeDTOList);
        return "notice/list";
    }

    // 공지사항 읽기
    @RequestMapping(value="/detail/{no}", method = RequestMethod.GET)
    public String read(@PathVariable("no") Long no, Model model){
        NoticeDTO noticeDTO = noticeService.read(no);
        model.addAttribute("noticeDTO",noticeDTO);
        return "board/detail.html";
    }

    @PostMapping ("/delete/{ntIdx}")
    public void delete(@PathVariable Long ntIdx){
        noticeService.delete(ntIdx);
    }
}
