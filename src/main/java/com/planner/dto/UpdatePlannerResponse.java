package com.planner.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePlannerResponse {
    // - 속성
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // - 생성자
    public UpdatePlannerResponse(Long id, String title, String contents, String name,LocalDateTime createdAt ,LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
