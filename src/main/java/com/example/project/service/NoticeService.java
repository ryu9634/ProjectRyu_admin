package com.example.project.service;

import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.Notice;
import com.example.project.repository.NoticeRepository;
import lombok.AllArgsConstructor;
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
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // 글 저장
    @Transactional
    public Notice create(NoticeDTO noticeDTO) {
        Notice notice = Notice.builder()
                .ntTitle(noticeDTO.getNtTitle())
                .ntRegdate(LocalDateTime.now())
                .ntContent(noticeDTO.getNtContent())
                .ntCategory(noticeDTO.getNtCategory())
                .ntHit(0)
                .build();
        Notice newNotice = noticeRepository.save(notice);
        return newNotice;
    }
    // 리스트
    @Transactional
    public List<NoticeDTO> getBoardList(){
        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        for(Notice notice : noticeList){
            NoticeDTO noticeDTO = NoticeDTO.builder()
                    .ntIdx(notice.getNtIdx())
                    .ntRegdate(notice.getNtRegdate())
                    .ntTitle(notice.getNtTitle())
                    .ntContent(notice.getNtContent())
                    .ntCategory(notice.getNtCategory())
                    .ntHit(notice.getNtHit())
                    .build();
            noticeDTOList.add(noticeDTO);
        }
        return noticeDTOList;
    }

    // 글 보기
    @Transactional
    public NoticeDTO read(Long id){
        Optional<Notice> noticeOptional = noticeRepository.findById(id);
        Notice notice = noticeOptional.get();
        NoticeDTO noticeDTO = NoticeDTO.builder()
                .ntIdx(notice.getNtIdx())
                .ntTitle(notice.getNtTitle())
                .ntRegdate(notice.getNtRegdate())
                .ntContent(notice.getNtContent())
                .ntCategory(notice.getNtCategory())
                .ntHit(notice.getNtHit())
                .ntRegdate(notice.getNtRegdate())
                .build();
        return noticeDTO;
    }

    // 글 수정
    @Transactional
    public void update(Long id, NoticeDTO noticeDTO){
        Optional<Notice> notice = noticeRepository.findById(id);

        notice.ifPresent(select -> {
            select.setNtTitle(noticeDTO.getNtTitle());
            select.setNtContent(noticeDTO.getNtContent());
            select.setNtCategory(noticeDTO.getNtCategory());
        });
    }

    // 글 삭제
    @Transactional
    public void delete(Long id){
        noticeRepository.deleteById(id);
    }


}
