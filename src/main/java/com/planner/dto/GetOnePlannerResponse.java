package com.planner.dto;

import com.planner.comment.dto.GetCommentResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class GetOnePlannerResponse {
    private final GetPlannerResponse getPlannerResponse;
    private final List<GetCommentResponse> commentsList;

    public GetOnePlannerResponse(GetPlannerResponse getPlannerResponse, List<GetCommentResponse> commentsList) {
        this.getPlannerResponse = getPlannerResponse;
        this.commentsList = commentsList;
    }
    }


