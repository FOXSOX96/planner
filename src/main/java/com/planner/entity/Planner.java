package com.planner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "planners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Planner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String title;
    @Column(length = 200, nullable = false)
    private String contents;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;

    public Planner(String title, String contents, String name, String password) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

    public void update(String title, String name, String password) {
        this.title = title;
        this.name = name;
        this.password = password;
    }

}
