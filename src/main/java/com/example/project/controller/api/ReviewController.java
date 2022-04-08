package com.example.project.controller.api;

import com.example.project.model.DTO.ReviewDTO;
import com.example.project.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody ReviewDTO reviewDTO){
        reviewService.create(userIdx, reviewDTO);
    }

    @GetMapping("/list/{userIdx}")
    public List<ReviewDTO> list(@PathVariable Long userIdx){
        List<ReviewDTO> reviewDTOList = reviewService.getReviewList(userIdx);
        return reviewDTOList;
    }


    @PostMapping("/delete/{rvIdx}")
    public void delete(@PathVariable Long rvIdx){
        reviewService.delete(rvIdx);
    }

}
