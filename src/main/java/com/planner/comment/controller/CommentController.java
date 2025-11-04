package com.planner.comment.controller;

import com.planner.comment.dto.CreateCommentRequest;
import com.planner.comment.dto.CreateCommentResponse;
import com.planner.comment.dto.GetCommentResponse;
import com.planner.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planners/{plannerId}/comments")
public class CommentController {
    private final CommentService commentService;

    //댓글 생성
    @PostMapping //@PathVariable로 plannerId를 전달받아 일정간 댓글을 분류하는데 사용
    public CreateCommentResponse createComment (@PathVariable Long plannerId, @RequestBody CreateCommentRequest request) {
     return commentService.createComment(plannerId, request);
    }

    //특정 일정의 댓글 조회
    @GetMapping
    public List<GetCommentResponse> getComment(@PathVariable Long plannerId) {
        return commentService.getComment(plannerId);
    }


}
