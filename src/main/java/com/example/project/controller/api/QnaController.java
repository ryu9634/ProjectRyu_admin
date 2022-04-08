package com.example.project.controller.api;

import com.example.project.model.DTO.FaqDTO;
import com.example.project.model.DTO.QnaDTO;
import com.example.project.service.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {


    private final QnaService qnaService;

    // 글작성
    //http://localhost:8080/qna/new
    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody QnaDTO qnaDTO)  {
        qnaService.create(userIdx, qnaDTO);
    }

    // 게시판 목록
    @GetMapping("/list/{userIdx}")
    public String list(Model model, @PathVariable Long userIdx){
        List<QnaDTO> qnaDTOList = qnaService.getBoardList(userIdx);
        model.addAttribute("qnaDTOList",qnaDTOList);
        return "faq/list";
    }

    @PostMapping("/update/{qIdx}")
    public void update(@PathVariable Long qIdx, @RequestBody QnaDTO qnaDTO){
        qnaService.update(qIdx, qnaDTO);
    }

    @PostMapping("/delete/{qIdx}")
    public void delete(@PathVariable Long qIdx){
        qnaService.delete(qIdx);
    }
}
