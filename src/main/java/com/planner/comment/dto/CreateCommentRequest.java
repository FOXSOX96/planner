package com.planner.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    // - 속성
    private String contents;
    private String name;
    private String password;
}
