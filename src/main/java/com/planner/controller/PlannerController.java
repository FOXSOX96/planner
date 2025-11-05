package com.planner.controller;

import com.planner.dto.*;
import com.planner.service.PlannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planners")
public class PlannerController {
    // - 속성
    private final PlannerService plannerService;

    // - 기능
    //일정생성
    @PostMapping
    public ResponseEntity<CreatePlannerResponse> createPlanner(@RequestBody CreatePlannerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(plannerService.createPlanner(request));
    }

    //선택일정 조회
    @GetMapping("/{plannerId}")
    public ResponseEntity<GetOnePlannerResponse> getOnePlanner(@PathVariable Long plannerId) {
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.getOnePlanner(plannerId));
    }

    //전체일정 조회
    @GetMapping
    public ResponseEntity<List<GetPlannerResponse>> getAllPlanner(@RequestParam(required = false) String name) { //생성자이지만 입력 안하도록 허용 -> null 전달
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.getAllPlanner(name));
    }

    //선택일정 업데이트
    @PatchMapping("/{plannerId}")
    public ResponseEntity<UpdatePlannerResponse> updatePlanner(@PathVariable Long plannerId, @RequestBody UpdatePlannerRequest request) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.updatePlanner(plannerId, request));
    }

    //선택일정 삭제
    @DeleteMapping("/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId, @RequestBody DeletePlannerRequest request) throws AccessDeniedException {
        plannerService.deletePlanner(plannerId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
