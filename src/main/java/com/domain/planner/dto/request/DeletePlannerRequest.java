package com.domain.planner.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class DeletePlannerRequest {
    // - 속성
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
