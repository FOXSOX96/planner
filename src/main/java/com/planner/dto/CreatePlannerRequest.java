package com.planner.dto;

import lombok.Getter;

@Getter
public class CreatePlannerRequest {
    private String title;
    private String contents;
    private String name;
    private String password;
}
