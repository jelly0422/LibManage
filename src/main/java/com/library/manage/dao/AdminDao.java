package com.library.manage.dao;

import com.library.manage.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDao extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByAccnum(String accnum);

    Optional<Admin> findByAccnumAndPassword(String accnum, String password);

    Optional<Admin> findAdminByAccnum(String accnum);
}
