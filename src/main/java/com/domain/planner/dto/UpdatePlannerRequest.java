package com.domain.planner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePlannerRequest {
    // - 속성
    @Size(max = 30)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
