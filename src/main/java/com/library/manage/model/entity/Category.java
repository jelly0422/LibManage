package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    /**
     * id
     */
    @Id
    @Column(name = "id", length = 10)
    private String id;

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
