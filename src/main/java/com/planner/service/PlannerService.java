package com.planner.service;

import com.planner.dto.*;
import com.planner.entity.Planner;
import com.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
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

        //이름값이 없으면 전체 일정 조회
        //이름값이 있으면 해당 이름의 일정만 전체 조회
        if (name == null) {
            planners = plannerRepository.findAll().stream()
                    .sorted(Comparator.comparing(Planner::getModifiedAt).reversed())
                    .toList();
        } else {
            planners = plannerRepository.findAll().stream()
                    .filter(planner -> planner.getName().equals(name))
                    .sorted(Comparator.comparing(Planner::getModifiedAt).reversed())
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

    //선택일정 업데이트
    @Transactional
    public UpdatePlannerResponse updatePlanner(Long plannerId, UpdatePlannerRequest request) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );

        if (planner.getPassword().equals(request.getPassword())) {
            planner.update(request.getTitle(), request.getName(), request.getPassword());
        } //패스워드를 서버로 전달하는 의미가 없는 것 같아서, 전달받았으니 검증까지 진행

        return new UpdatePlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getName(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );


    }

    //선택일정 삭제
    @Transactional
    public void deletePlanner(Long plannerId, DeletePlannerRequest request) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );
        if (planner.getPassword().equals(request.getPassword())) {
            plannerRepository.deleteById(plannerId);
        } //패스워드를 서버로 전달하는 의미가 없는 것 같아서, 전달받았으니 검증까지 진행

    }
}
