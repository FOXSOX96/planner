package com.planner.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {
    private String contents;
    private String name;
    private String password;
}
