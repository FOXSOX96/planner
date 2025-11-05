package com.domain.comment.service;

import com.domain.comment.dto.request.CreateCommentRequest;
import com.domain.comment.dto.response.CreateCommentResponse;
import com.domain.comment.dto.response.GetCommentResponse;
import com.domain.comment.entity.Comment;
import com.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CommentService {
    // - 속성
    private final CommentRepository commentRepository;

    // - 기능
    //region 댓글 생성
    @Transactional
    public CreateCommentResponse createComment(Long plannerId, CreateCommentRequest request) {
        Comment comment = new Comment(
                plannerId, //@PathVariable 로 전달받아와서 일정간 댓글을 구분
                request.getContents(),
                request.getName(),
                request.getPassword()
        );
        Comment savedComment = commentRepository.save(comment);
        if (commentsOfPlan(plannerId).count() > 10) { //특정 일정에 댓글갯수가 10개 넘으면 저장하지 않음
            commentRepository.delete(comment); //계속 쌓이는 것 방지
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
    //endregion

    //region 선택일정의 댓글 조회
    @Transactional(readOnly = true)
    public List<GetCommentResponse> getComment(Long plannerId) {
        List<Comment> comments = commentsOfPlan(plannerId).toList(); //특정 일정에 달린 댓글만 리스트화
        List<GetCommentResponse> dtos = new ArrayList<>();

        for (Comment comment : comments) {
            GetCommentResponse dto = new GetCommentResponse(
                    comment.getId(),
                    comment.getPlannerId(),
                    comment.getContents(),
                    comment.getName(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    //endregion

    //region 특정 일정에 달린 댓글만 조회하는 Stream
    private Stream<Comment> commentsOfPlan(Long plannerId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getPlannerId().equals(plannerId));
    }
    //endregion
}
