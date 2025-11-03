package com.planner.service;

import com.planner.dto.CreatePlannerRequest;
import com.planner.dto.CreatePlannerResponse;
import com.planner.dto.GetPlannerResponse;
import com.planner.entity.Planner;
import com.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private final PlannerRepository plannerRepository;

    //일정생성
    @Transactional
    public CreatePlannerResponse createPlanner(CreatePlannerRequest request) {
        Planner planner = new Planner(
                request.getTitle(),
                request.getContents(),
                request.getName(),
                request.getPassword()
        );
        Planner savedPlanner = plannerRepository.save(planner);
        return new CreatePlannerResponse(
                savedPlanner.getId(),
                savedPlanner.getTitle(),
                savedPlanner.getContents(),
                savedPlanner.getName(),
                savedPlanner.getCreatedAt(),
                savedPlanner.getModifiedAt()
        );
    }

    //선택일정 조회
    @Transactional(readOnly = true)
    public GetPlannerResponse getOnePlanner(Long plannerId) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );
        return new GetPlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getName(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );
    }

    //전체일정 조회
    @Transactional(readOnly = true)
    public List<GetPlannerResponse> getAllPlanner(String name) {
        List<Planner> planners;

        if (name == null) {
            planners = plannerRepository.findAll();
        } else {
            planners = plannerRepository.findAll().stream()
                    .filter(planner -> planner.getName().equals(name))
                    .toList();
        }
        List<GetPlannerResponse> dtos = new ArrayList<>();

        for (Planner planner : planners) {
            GetPlannerResponse dto = new GetPlannerResponse(
                    planner.getId(),
                    planner.getTitle(),
                    planner.getContents(),
                    planner.getName(),
                    planner.getCreatedAt(),
                    planner.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
}
