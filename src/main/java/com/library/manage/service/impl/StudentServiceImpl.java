package com.library.manage.service.impl;

import com.library.manage.dao.StudentDao;
import com.library.manage.model.dto.StudentDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Student;
import com.library.manage.model.enums.Sex;
import com.library.manage.model.param.LoginParam;
import com.library.manage.model.param.StuRegistParam;
import com.library.manage.model.param.UpdateStuStatusParam;
import com.library.manage.model.vo.StudentVO;
import com.library.manage.service.BorrowInfoService;
import com.library.manage.service.CommentService;
import com.library.manage.service.FavorService;
import com.library.manage.service.StudentService;
import com.library.manage.util.BeanUtil;
import com.library.manage.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BorrowInfoService borrowInfoService;
    @Autowired
    private FavorService favorService;
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Override
    public StudentDTO stuLogin(LoginParam loginParam) {
        Optional<Student> student = studentDao.findById(loginParam.getStuno());
        return student.map(value -> {
            if (value.getPassword().equals(loginParam.getPassword()))
                return BeanUtil.entityToDTO(StudentDTO.class, value);
            return null;
        }).orElse(null);
    }

    @Override
    @Transactional
    public String stuRegist(StuRegistParam stuRegistParam) {
        Assert.notNull(stuRegistParam, "注册信息不能为空");

        StringBuilder stringBuilder = new StringBuilder();

        if (!studentDao.existsByStuno(stuRegistParam.getStuno())){
            studentDao.save(Objects.requireNonNull(BeanUtil.transformFrom(stuRegistParam, Student.class)));
            stringBuilder.append("注册成功");
        }else{
            stringBuilder.append("学号或者邮箱已存在");
        }

        return stringBuilder.toString();
    }

    @Override
    public String updatePassword(String oldPwd, String newPwd, String stuno) {
        Optional<Student> student = studentDao.findByStunoAndPassword(stuno, oldPwd);
        StringBuilder stringBuilder = new StringBuilder();
        if (student.isEmpty()){
            stringBuilder.append("旧密码不正确");
        }else {
            student.get().setPassword(newPwd);
            studentDao.saveAndFlush(student.get());
            stringBuilder.append("修改成功");
        }

        return stringBuilder.toString();
    }

    @Override
    public StudentDTO updateStuInfo(String stuno, String key, String val) {
        Optional<Student> student1 = studentDao.findById(stuno);

        switch(key){
            case "email" -> student1.map(student -> {
                student.setEmail(val);
                return student;
            });
            case "name" -> student1.map(student -> {
                student.setName(val);
                return student;
            });
            case "sex" -> student1.map(student -> {
                student.setSex(Sex.valueOf(val));
                return student;
            });
            default -> {}
        }

        return student1.map(student -> BeanUtil.entityToDTO(StudentDTO.class, studentDao.save(student))).orElse(null);
    }

    @Override
    public StudentVO intoStuCenter(StudentDTO studentDTO) {
        String stuno = studentDTO.getStuno();
        return StudentVO.builder().studentDTO(studentDTO)
                .comments(commentService.getStuComment(stuno))
                .borrowInfos(borrowInfoService.stuGetBorrowInfo(stuno))
                .favorVO(favorService.getFavorVO(stuno)).build();

    }

    @Override
    public List<Student> getAll() {
        return studentDao.findAll();
    }

    @Override
    public Student updateStatus(UpdateStuStatusParam statusParam) {
        return studentDao.findById(statusParam.getStuno()).map(student -> {
            student.setStatus(statusParam.getStatus());
            studentDao.saveAndFlush(student);
            return student;
        }).orElseThrow();
    }

    @Override
    public String sendEmail(String email){
        if (!studentDao.existsByEmail(email)) {
            String code = MailUtil.code();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码（有效期10分钟）");
            mailMessage.setText(code);
            mailMessage.setTo(email);
            mailMessage.setFrom("1187893119@qq.com");
            mailSender.send(mailMessage);
            return code;
        }
        return "该邮箱已被使用";
    }

    @Override
    public BorrowInfo borrowBook() {
        return null;
    }
}
