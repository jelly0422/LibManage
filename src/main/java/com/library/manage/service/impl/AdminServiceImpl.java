package com.library.manage.service.impl;

import com.library.manage.dao.AdminDao;
import com.library.manage.model.dto.AdminDTO;
import com.library.manage.model.entity.Admin;
import com.library.manage.service.AdminService;
import com.library.manage.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public AdminDTO adminLogin(String accnum, String password) {
        Optional<Admin> admin = adminDao.findByAccnum(accnum);

        return admin.filter(value -> value.getPassword().equals(password)).map(value -> BeanUtil.entityToDTO(AdminDTO.class, value)).orElse(null);
    }

    @Override
    public String adminResetPwd(String newPwd, String oldPwd, String accnum) {
        Optional<Admin> admin = adminDao.findByAccnum(accnum);
        StringBuilder stringBuilder = new StringBuilder();
        if (admin.isPresent()) {
            Admin admin1 = admin.get();
            if (admin1.getPassword().equals(oldPwd)) {
                admin1.setPassword(newPwd);
                adminDao.saveAndFlush(admin1);
                stringBuilder.append("重置成功");
            }else
                stringBuilder.append("旧密码不正确");
        }
        return stringBuilder.toString();
    }
}
