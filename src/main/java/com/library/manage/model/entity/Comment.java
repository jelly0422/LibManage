package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "content", nullable = false, length = 200)
    private String content;

    @Column(name = "release_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date releasetime;

    @Column(name = "ISBN", nullable = false, length = 13)
    private String ISBN;

    @Column(name = "author", nullable = false, length = 20)
    private String author;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "writer", nullable = false)
    private String writer;
}
