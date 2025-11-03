package com.planner.controller;

import com.planner.dto.CreatePlannerRequest;
import com.planner.dto.CreatePlannerResponse;
import com.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerService plannerService;

    //일정생성
    @PostMapping("/planners")
    public CreatePlannerResponse createPlanner(@RequestBody CreatePlannerRequest request) {
        return plannerService.create(request);
    }

}
