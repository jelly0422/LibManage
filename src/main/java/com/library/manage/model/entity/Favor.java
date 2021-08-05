package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * 收藏夹
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "favor")
public class Favor {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 归属者
     */
    @Column(name = "belong_stu", nullable = false, length = 20)
    private String belongstu;

    /**
     * ISBN
     */
    @Column(name = "ISBN", nullable = false, length = 13)
    private String ISBN;
}
