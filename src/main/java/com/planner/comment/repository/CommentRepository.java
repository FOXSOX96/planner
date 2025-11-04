package com.planner.comment.repository;

import com.planner.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    long countByPlannerId(Long plannerId);
}
