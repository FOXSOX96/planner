package com.domain.planner.service;

import com.domain.comment.dto.response.GetCommentResponse;
import com.domain.comment.entity.Comment;
import com.domain.comment.repository.CommentRepository;
import com.domain.planner.dto.request.CreatePlannerRequest;
import com.domain.planner.dto.request.DeletePlannerRequest;
import com.domain.planner.dto.request.UpdatePlannerRequest;
import com.domain.planner.dto.response.CreatePlannerResponse;
import com.domain.planner.dto.response.GetOnePlannerResponse;
import com.domain.planner.dto.response.GetPlannerResponse;
import com.domain.planner.dto.response.UpdatePlannerResponse;
import com.domain.planner.entity.Planner;
import com.domain.planner.repository.PlannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlannerService {
    // - 속성
    /**Planner패키지 > Comment패키지 포함*/
    private final PlannerRepository plannerRepository;
    private final CommentRepository commentRepository;

    // - 기능
    //region 일정생성
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
    //endregion

    //region 선택일정 조회
    @Transactional(readOnly = true)
    public GetOnePlannerResponse getOnePlanner(Long plannerId) {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );
        GetPlannerResponse getPlannerResponse = new GetPlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getName(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );
        //선택일정의 댓글 조회 (CommentService클래스에서 그대로 가져옴)
        List<Comment> comments = commentsOfPlan(plannerId).toList(); //특정 일정에 달린 댓글만 리스트화
        List<GetCommentResponse> commentsList = new ArrayList<>();

        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getPlannerId(),
                    comment.getContents(),
                    comment.getName(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            commentsList.add(dto);
        }
        return new GetOnePlannerResponse(getPlannerResponse, commentsList);
    }
    //endregion

    //region 전체일정 조회
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
    //endregion

    //region 선택일정 업데이트
    @Transactional
    public UpdatePlannerResponse updatePlanner(Long plannerId, UpdatePlannerRequest request) throws AuthenticationException {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );

        if (planner.getPassword().equals(request.getPassword())) {
            planner.update(request.getTitle(), request.getName(), request.getPassword());
        } //패스워드를 서버로 전달하는 의미가 없는 것 같아서, 전달받았으니 검증까지 진행
        else {
            throw new AuthenticationException("비밀번호가 일치하지 않습니다.");
        }

        return new UpdatePlannerResponse(
                planner.getId(),
                planner.getTitle(),
                planner.getContents(),
                planner.getName(),
                planner.getCreatedAt(),
                planner.getModifiedAt()
        );


    }
    //endregion

    //region 선택일정 삭제
    @Transactional
    public void deletePlanner(Long plannerId, DeletePlannerRequest request) throws AccessDeniedException {
        Planner planner = plannerRepository.findById(plannerId).orElseThrow(
                () -> new IllegalArgumentException("플래너 ID " + plannerId + "에 해당하는 플래너가 없습니다.")
        );
        if (planner.getPassword().equals(request.getPassword())) {
            plannerRepository.deleteById(plannerId);
        } //패스워드를 서버로 전달하는 의미가 없는 것 같아서, 전달받았으니 검증까지 진행
        else {
            throw new AccessDeniedException("비밀번호가 일치하지 않습니다.");
        }

    }
    //endregion

    //region 특정 일정에 달린 댓글만 조회하는 Stream (Extracted 매서드)
    private Stream<Comment> commentsOfPlan(Long plannerId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPlannerId().equals(plannerId));
    }
    //endregion

}
