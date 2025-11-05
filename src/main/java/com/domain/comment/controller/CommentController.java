package com.domain.comment.controller;

import com.domain.comment.dto.request.CreateCommentRequest;
import com.domain.comment.dto.response.CreateCommentResponse;
import com.domain.comment.dto.response.GetCommentResponse;
import com.domain.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planners/{plannerId}/comments")
public class CommentController {
    // - 속성
    private final CommentService commentService;

    // - 기능
    //댓글 생성
    @PostMapping //@PathVariable로 plannerId를 전달받아 일정간 댓글을 분류하는데 사용
    public ResponseEntity<CreateCommentResponse> createComment (@PathVariable Long plannerId, @RequestBody @Valid CreateCommentRequest request) {
     return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(plannerId, request));
    }

    //특정 일정의 댓글 조회
    @GetMapping
    public ResponseEntity<List<GetCommentResponse>> getComment(@PathVariable Long plannerId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(plannerId));
    }


}
