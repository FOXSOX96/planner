package com.planner.dto;

import com.planner.comment.dto.GetCommentResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnePlannerResponse {
    // - 속성
    private final GetPlannerResponse getPlannerResponse;
    private final List<GetCommentResponse> commentsList; //댓글목록

    // - 생성자
    public GetOnePlannerResponse(GetPlannerResponse getPlannerResponse, List<GetCommentResponse> commentsList) {
        this.getPlannerResponse = getPlannerResponse;
        this.commentsList = commentsList;
    }
    }


