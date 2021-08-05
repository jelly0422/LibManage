package com.library.manage.service;

import com.library.manage.model.dto.StudentDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Student;
import com.library.manage.model.param.LoginParam;
import com.library.manage.model.param.StuRegistParam;
import com.library.manage.model.param.UpdateStuStatusParam;
import com.library.manage.model.vo.StudentVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public interface StudentService {

    StudentDTO stuLogin(LoginParam loginParam);

    String stuRegist(StuRegistParam stuRegistParam);

    String updatePassword(String oldPwd, String newPwd, String stuno);

    StudentDTO updateStuInfo(String stuno, String key, String val);

    StudentVO intoStuCenter(StudentDTO studentDTO);

    List<Student> getAll();

    Student updateStatus(UpdateStuStatusParam statusParam);

    String sendEmail(String email);

    BorrowInfo borrowBook();
}
