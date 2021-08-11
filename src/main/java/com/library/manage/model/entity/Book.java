package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * book
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * ISBN码
     */
    @Column(name = "ISBN", nullable = false, length = 13, unique = true)
    private String ISBN;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 库存
     */
    @Column(name = "stock", nullable = false)
    private Integer stock;

    /**
     * 作者
     */
    @Column(name = "writer", nullable = false, length = 20)
    private String writer;

    /**
     * 评分
     */
    @Column(name = "score")
    private Double score = 0.0d;

    /**
     * 评分人数
     */
    @Column(name = "score_num")
    private Integer scorenum = 0;

    /**
     * 出版方
     */
    @Column(name = "publisher", nullable = false, length = 50)
    private String publisher;

    /**
     * 简介
     */
    @Column(name = "introduction", nullable = false)
    private String introduction;

    /**
     * 类别
     */
    @Column(name = "category", nullable = false, length = 10)
    private String category;

    /**
     * 馆藏位置
     */
    @Column(name = "position", nullable = false, length = 50)
    private String position;

    /**
     * 借出数量
     */
    @Column(name = "borrowed_num", nullable = false)
    private Integer borrowednum = 0;
}
