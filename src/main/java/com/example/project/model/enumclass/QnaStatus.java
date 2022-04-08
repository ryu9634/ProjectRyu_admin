package com.example.project.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QnaStatus {
    FIN(0, "답변완료", "답변 완료 상태"),
    ING(1, "답변대기", "답변 대기 상태");

    private Integer id;
    private String title;
    private String description;
}
