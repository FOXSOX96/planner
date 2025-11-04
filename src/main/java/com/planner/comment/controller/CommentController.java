package com.planner.comment.controller;

import com.planner.comment.dto.CreateCommentRequest;
import com.planner.comment.dto.CreateCommentResponse;
import com.planner.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planners/{plannerId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping //@PathVariable로 plannerId를 전달받아 일정간 댓글을 분류하는데 사용
    public CreateCommentResponse createComment (@PathVariable Long plannerId, @RequestBody CreateCommentRequest request) {
     return commentService.createComment(plannerId, request);
    }

}
