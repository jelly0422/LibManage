package com.library.manage.service;

import com.library.manage.model.dto.AdminDTO;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    AdminDTO adminLogin(String accnum, String password);

    String adminResetPwd(String newPwd, String oldPwd, String id);
}
