package com.planner.controller;

import com.planner.dto.*;
import com.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlannerController {
    private final PlannerService plannerService;

    //일정생성
    @PostMapping("/planners")
    public CreatePlannerResponse createPlanner(@RequestBody CreatePlannerRequest request) {
        return plannerService.createPlanner(request);
    }

    //선택일정 조회
    @GetMapping("/planners/{plannerId}")
    public GetPlannerResponse getOnePlanner(@PathVariable Long plannerId) {
        return plannerService.getOnePlanner(plannerId);
    }

    //전체일정 조회
    @GetMapping("/planners")
    public List<GetPlannerResponse> getAllPlanner(@RequestParam(required = false) String name) { //생성자이지만 입력 안하도록 허용 -> null 전달
        return plannerService.getAllPlanner(name);
    }

    //선택일정 업데이트
    @PatchMapping("/planners/{plannerId}")
    public UpdatePlannerResponse updatePlanner(@PathVariable Long plannerId, @RequestBody UpdatePlannerRequest request) {
        return plannerService.updatePlanner(plannerId, request);
    }

    //선택일정 삭제
    @DeleteMapping("/planners/{plannerId}")
    public void deletePlanner(@PathVariable Long plannerId, @RequestBody DeletePlannerRequest request) {
        plannerService.deletePlanner(plannerId, request);
    }


}
