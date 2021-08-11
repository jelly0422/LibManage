package com.library.manage.model.entity;

import com.library.manage.model.enums.Sex;
import com.library.manage.model.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * 学生
 * @author jelly
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 姓名
     */
    @Column(name = "stu_name", nullable = false, length = 50)
    private String name;

    /**
     * 学号
     */
    @Column(name = "stu_no", nullable = false, length = 20, unique = true)
    private String stuno;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    /**
     * 账号状态
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /**
     * 性别
     */
    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    /**
     * 邮箱
     */
    @Column(name = "email", nullable = false, unique = true, length = 30)
    private String email;
}
