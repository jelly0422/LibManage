package com.library.manage.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * administrator
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "admin")
public class Admin {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    @Column(name = "acc_num", nullable = false, length = 20, unique = true)
    private String accnum;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, length = 30)
    private String password;
}
