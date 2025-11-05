package com.planner.dto;

import lombok.Getter;

@Getter
public class CreatePlannerRequest {
    // - 속성
    private String title;
    private String contents;
    private String name;
    private String password;
}
