package com.library.manage.model.entity;

import com.library.manage.model.enums.IsInTime;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 借阅信息
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "borrowinfo")
public class BorrowInfo {

    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * 借阅人
     */
    @Column(name = "borrow_stu", nullable = false, length = 20)
    private String borrowstu;

    /**
     * ISBN
     */
    @Column(name = "ISBN", nullable = false, length = 13)
    private String ISBN;

    /**
     * 类别
     */
    @Column(name = "category", nullable = false, length = 10)
    private String category;

    /**
     * 借阅时间
     */
    @Column(name = "borrow_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowtime;

    /**
     * 借阅天数
     */
    @Column(name = "read_time", nullable = false)
    private Integer readtime;

    @Column(name = "ret_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rettime;

    @Column(name = "is_intime")
    @Enumerated(EnumType.STRING)
    private IsInTime isInTime;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "renew", nullable = false)
    private Boolean renew = false;
}
