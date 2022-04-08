package com.example.project.service;

import com.example.project.model.DTO.ReviewDTO;
import com.example.project.model.entity.Review;
import com.example.project.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Transactional
    public Review create(Long userIdx, ReviewDTO reviewDTO){
        Review review = Review.builder()
                .userIdx(userIdx)
                .rvContent(reviewDTO.getRvContent())
                .rvRegdate(LocalDateTime.now())
                .rvImg(reviewDTO.getRvImg())
                .rvStar(reviewDTO.getRvStar())
                .rvTitle(reviewDTO.getRvTitle())
                .build();
        Review newReview = reviewRepository.save(review);
        return newReview;
    }

    @Transactional
    public List<ReviewDTO> getReviewList(Long userIdx){
        List<Review> reviewList = reviewRepository.findAllByUserIdx(userIdx);
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .rvIdx(review.getRvIdx())
                    .userIdx(review.getUserIdx())
                    .rvContent(review.getRvContent())
                    .rvTitle(review.getRvTitle())
                    .rvStar(review.getRvStar())
                    .rvImg(review.getRvImg())
                    .rvRegdate(review.getRvRegdate())
                    .build();
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    @Transactional
    public List<ReviewDTO> list(){
        List<Review> reviewList = reviewRepository.findAll();
        List<ReviewDTO> reviewDTOList = new ArrayList<>();

        for(Review review : reviewList){
            ReviewDTO reviewDTO = ReviewDTO.builder()
                    .rvIdx(review.getRvIdx())
                    .userIdx(review.getUserIdx())
                    .rvContent(review.getRvContent())
                    .rvTitle(review.getRvTitle())
                    .rvStar(review.getRvStar())
                    .rvImg(review.getRvImg())
                    .rvRegdate(review.getRvRegdate())
                    .build();
            reviewDTOList.add(reviewDTO);
        }
        return reviewDTOList;
    }

    @Transactional
    public ReviewDTO read(Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        Review review = reviewOptional.get();
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .rvIdx(review.getRvIdx())
                .userIdx(review.getUserIdx())
                .rvContent(review.getRvContent())
                .rvTitle(review.getRvTitle())
                .rvStar(review.getRvStar())
                .rvImg(review.getRvImg())
                .rvRegdate(review.getRvRegdate())
                .build();
        return reviewDTO;

        }

    @Transactional
    public void delete(Long id){
        reviewRepository.deleteById(id);
    }
}

