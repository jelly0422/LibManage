package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 类别
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * id
     */
    @Column(name = "id", length = 10, unique = true)
    private String code;

    /**
     * 介绍
     */
    @Column(name = "description", nullable = false, length = 80)
    private String description;

    /**
     * 父分类id
     */
    @Column(name = "parent_id", length = 10)
    private String parentid;
}
