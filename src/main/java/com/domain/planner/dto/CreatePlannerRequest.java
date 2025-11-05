package com.domain.planner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePlannerRequest {
    // - 속성
    @Size(max = 30)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @Size(max = 200)
    @NotBlank(message = "내용을 입력해주세요.")
    private String contents;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
