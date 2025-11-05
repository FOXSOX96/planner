package com.domain.planner.controller;

import com.domain.planner.dto.request.CreatePlannerRequest;
import com.domain.planner.dto.request.DeletePlannerRequest;
import com.domain.planner.dto.request.UpdatePlannerRequest;
import com.domain.planner.dto.response.CreatePlannerResponse;
import com.domain.planner.dto.response.GetOnePlannerResponse;
import com.domain.planner.dto.response.GetPlannerResponse;
import com.domain.planner.dto.response.UpdatePlannerResponse;
import com.domain.planner.service.PlannerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
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
    public ResponseEntity<CreatePlannerResponse> createPlanner(@RequestBody @Valid CreatePlannerRequest request) {
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
    public ResponseEntity<UpdatePlannerResponse> updatePlanner(@PathVariable Long plannerId, @RequestBody @Valid UpdatePlannerRequest request) throws AuthenticationException {
        return ResponseEntity.status(HttpStatus.OK).body(plannerService.updatePlanner(plannerId, request));
    }

    //선택일정 삭제
    @DeleteMapping("/{plannerId}")
    public ResponseEntity<Void> deletePlanner(@PathVariable Long plannerId, @RequestBody @Valid DeletePlannerRequest request) throws AccessDeniedException {
        plannerService.deletePlanner(plannerId, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
