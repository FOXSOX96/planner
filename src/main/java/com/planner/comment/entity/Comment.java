package com.planner.comment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name ="comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long plannerId;
    @Column(length = 100, nullable = false)
    private String contents;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public Comment(Long plannerId, String contents, String name, String password) {
        this.plannerId = plannerId;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

    public void update(Long plannerId, String contents, String name, String password) {
        this.plannerId = plannerId;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

}
