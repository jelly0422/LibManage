package com.library.manage.dao;

import com.library.manage.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface StudentDao extends JpaRepository<Student, String> {

    boolean existsByEmail(String email);

    boolean existsByStuno(String stu_no);

    Optional<Student> findByStunoAndPassword(String stuno, String password);

    @Transactional
    @Modifying
    @Query(value = "update student set #{#key}=#{#val} where stu_no=#{#stuno};", nativeQuery = true)
    Integer updateStu(@Param("stuno") String stuno,@Param("key") String key,@Param("val") String val);
}
