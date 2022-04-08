package com.example.project.service;

import com.example.project.model.DTO.FaqDTO;
import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.Faq;
import com.example.project.model.entity.Notice;
import com.example.project.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    // 글작성
    @Transactional
    public Faq create(FaqDTO faqDTO){
        Faq faq = Faq.builder()
                .faqTitle(faqDTO.getFaqTitle())
                .faqAnswer(faqDTO.getFaqAnswer())
                .faqImg(faqDTO.getFaqImg())
                .faqCategory(faqDTO.getFaqCategory())
                .build();
        Faq newFaq = faqRepository.save(faq);
        return newFaq;
    }

    // 리스트
    @Transactional
    public List<FaqDTO> getBoardList(){
        List<Faq> faqList = faqRepository.findAll();
        List<FaqDTO> faqDTOList = new ArrayList<>();

        for(Faq faq : faqList){
            FaqDTO faqDTO = FaqDTO.builder()
                    .faqTitle(faq.getFaqTitle())
                    .faqAnswer(faq.getFaqAnswer())
                    .faqCategory(faq.getFaqCategory())
                    .faqImg(faq.getFaqImg())
                    .build();
            faqDTOList.add(faqDTO);
        }
        return faqDTOList;
    }

    // 글 보기
    public FaqDTO read(Long id){
        Optional<Faq> faqOptional = faqRepository.findById(id);
        Faq faq = faqOptional.get();
        FaqDTO faqDTO = FaqDTO.builder()
                .faqTitle(faq.getFaqTitle())
                .faqAnswer(faq.getFaqAnswer())
                .faqCategory(faq.getFaqCategory())
                .faqImg(faq.getFaqImg())
                .build();
        return faqDTO;
    }

    // 글 수정
    @Transactional
    public void update(Long id, FaqDTO faqDTO){
        Optional<Faq> faq = faqRepository.findById(id);

        faq.ifPresent(select -> {
            select.setFaqTitle(faqDTO.getFaqTitle());
            select.setFaqAnswer(faqDTO.getFaqAnswer());
            select.setFaqCategory(faqDTO.getFaqCategory());
            select.setFaqImg(faqDTO.getFaqImg());
        });
    }

    // 글 삭제
    @Transactional
    public void delete(Long id){
        faqRepository.deleteById(id);
    }
}
