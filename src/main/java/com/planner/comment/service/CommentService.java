package com.planner.comment.service;

import com.planner.comment.dto.CreateCommentRequest;
import com.planner.comment.dto.CreateCommentResponse;
import com.planner.comment.entity.Comment;
import com.planner.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 생성
    @Transactional
    public CreateCommentResponse createComment(Long plannerId, CreateCommentRequest request) {
        Comment comment = new Comment(
                plannerId, //@PathVariable 로 전달받아와서 일정간 댓글을 구분
                request.getContents(),
                request.getName(),
                request.getPassword()
        );
        Comment savedComment = commentRepository.save(comment);
        if (commentRepository.countByPlannerId(plannerId) > 10) { //특정 일정에 댓글갯수가 10개 넘으면 저장하지 않음
            commentRepository.delete(comment);
            throw new IllegalStateException("댓글은 최대 10개까지만 등록할 수 있습니다.");
        } else {
            return new CreateCommentResponse(
                    savedComment.getId(),
                    savedComment.getPlannerId(),
                    savedComment.getContents(),
                    savedComment.getName(),
                    savedComment.getCreatedAt(),
                    savedComment.getModifiedAt()
            );
        }
    }
}
