package com.planner.comment.controller;

import com.planner.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planners/{plannerId}/comments")
public class CommentController {
    private final CommentService commentService;
}
