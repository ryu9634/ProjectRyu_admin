package com.example.project.service;

import com.example.project.model.DTO.FaqDTO;
import com.example.project.model.DTO.QnaDTO;
import com.example.project.model.entity.Faq;
import com.example.project.model.entity.Qna;
import com.example.project.model.entity.User;
import com.example.project.model.enumclass.QnaStatus;
import com.example.project.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QnaService {

    @Autowired
    private QnaRepository qnaRepository;

    // 질문 작성
    @Transactional
    public Qna create(Long userIdx, QnaDTO qnaDTO) {
        Qna qna = Qna.builder()
                .userIdx(userIdx)
                .qTitle(qnaDTO.getQTitle())
                .qNumber(qnaDTO.getQNumber())
                .qAnswer(qnaDTO.getQAnswer())
                .qContent(qnaDTO.getQContent())
                .qCategory(qnaDTO.getQCategory())
                .qStatus(QnaStatus.ING)
                .qRegdate(LocalDateTime.now())
                .build();
        Qna newQna = qnaRepository.save(qna);
        return newQna;
    }

    // 리스트
    @Transactional
    public List<QnaDTO> getBoardList(Long userIdx){
        List<Qna> qnaList = qnaRepository.findAllByUserIdx(userIdx);
        List<QnaDTO> faqDTOList = new ArrayList<>();

        for(Qna qna : qnaList){
            QnaDTO qnaDTO = QnaDTO.builder()
                    .qTitle(qna.getQTitle())
                    .qAnswer(qna.getQAnswer())
                    .qContent(qna.getQContent())
                    .qCategory(qna.getQCategory())
                    .qStatus(qna.getQStatus())
                    .qRegdate(qna.getQRegdate())
                    .qNumber(qna.getQNumber())
                    .userIdx(qna.getUserIdx())
                    .build();
            faqDTOList.add(qnaDTO);
        }
        return faqDTOList;
    }

    @Transactional
    public List<QnaDTO> list(){
        List<Qna> qnaList = qnaRepository.findAll();
        List<QnaDTO> faqDTOList = new ArrayList<>();

        for(Qna qna : qnaList){
            QnaDTO qnaDTO = QnaDTO.builder()
                    .qIdx(qna.getQIdx())
                    .qTitle(qna.getQTitle())
                    .qAnswer(qna.getQAnswer())
                    .qContent(qna.getQContent())
                    .qCategory(qna.getQCategory())
                    .qStatus(qna.getQStatus())
                    .qRegdate(qna.getQRegdate())
                    .qNumber(qna.getQNumber())
                    .userIdx(qna.getUserIdx())
                    .build();
            faqDTOList.add(qnaDTO);
        }
        return faqDTOList;
    }
    // 글 보기
    @Transactional
    public QnaDTO read(Long id){
        Optional<Qna> qnaOptional = qnaRepository.findById(id);
        Qna qna = qnaOptional.get();
        QnaDTO qnaDTO = QnaDTO.builder()
                .qIdx(qna.getQIdx())
                .qTitle(qna.getQTitle())
                .qAnswer(qna.getQAnswer())
                .qContent(qna.getQContent())
                .qCategory(qna.getQCategory())
                .qStatus(qna.getQStatus())
                .qRegdate(qna.getQRegdate())
                .qNumber(qna.getQNumber())
                .userIdx(qna.getUserIdx())
                .build();
        return qnaDTO;
    }

    // 글 수정
    @Transactional
    public void update(Long id, QnaDTO qnaDTO){
        Optional<Qna> qna = qnaRepository.findById(id);

        qna.ifPresent(select -> {
            select.setQAnswer(qnaDTO.getQAnswer());
            select.setQStatus(QnaStatus.FIN);
        });
    }


    // 글 삭제
    @Transactional
    public void delete(Long id){
        qnaRepository.deleteById(id);
    }
}
