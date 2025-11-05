package com.domain.comment.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    // - 속성
    private final Long id;
    private final Long plannerId;
    private final String contents;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // - 생성자
    public CreateCommentResponse(Long id,Long plannerId, String contents, String name, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.plannerId = plannerId;
        this.contents = contents;
        this.name = name;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
