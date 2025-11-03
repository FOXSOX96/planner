package com.planner.service;

import com.planner.dto.CreatePlannerRequest;
import com.planner.dto.CreatePlannerResponse;
import com.planner.entity.Planner;
import com.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlannerService {
    private final PlannerRepository plannerRepository;

    //일정생성
    @Transactional
    public CreatePlannerResponse create(CreatePlannerRequest request) {
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
}
