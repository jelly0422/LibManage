package com.library.manage;

import com.library.manage.dao.BookDao;
import com.library.manage.dao.BorrowInfoDao;
import com.library.manage.dao.CommentDao;
import com.library.manage.dao.FavorDao;
import com.library.manage.model.dto.BorrowInfoDTO;
import com.library.manage.model.dto.CommentDTO;
import com.library.manage.model.entity.BorrowInfo;
import com.library.manage.model.entity.Comment;
import com.library.manage.model.vo.BookVO;
import com.library.manage.service.BookService;
import com.library.manage.service.StudentService;
import com.library.manage.util.BeanUtil;
import com.library.manage.util.DateUtil;
import com.library.manage.util.Sftp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ManageApplicationTests {
    @Autowired
    JavaMailSenderImpl mailSender;
    @Autowired
    StudentService studentService;
    @Autowired
    BookDao bookDao;
    @Autowired
    FavorDao favorDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    BorrowInfoDao borrowInfoDao;
    @Autowired
    BookService bookService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    void test(){
        Sftp sftp = new Sftp("root", "Chen3119", "8.135.28.126", 22);
        sftp.login();

    }

    @Test
    void testHot(){
        List<Map<String, Object>> scoreAndISBN = favorDao.getFavorCategory("201821314124");
        scoreAndISBN.forEach(map -> {
            map.forEach((k,v) ->{
                System.out.println(k + " : " + v);
            });
        });
    }

    @Test
    void testDate() throws ParseException {
        System.out.println(DateUtil.dateFormat_To_Day(new Date()));

        // 使用format()方法将日期转换为指定格式的文本
SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
// 创建Date对象，表示当前时间
Date now = new Date();

 // 调用format()方法，将日期转换为字符串并输出
System.out.println(sdf1.format(now));
System.out.println(sdf2.format(now));
System.out.println(sdf3.format(now));

// 使用parse()方法将文本转换为日期
String d = "2014-6-1 21:05:36";
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

// 调用parse()方法，将字符串转换为日期
Date date = sdf.parse(d);
            System.out.println(date);
    }

    @Test
    void testBean(){
        List<Comment> byAuthor = commentDao.findByAuthor("201821314223");
        byAuthor.forEach(comment -> System.out.println(BeanUtil.entityToDTO(CommentDTO.class, comment)));
        Optional<BorrowInfo> byId = borrowInfoDao.findById(5);
        System.out.println(BeanUtil.entityToDTO(BorrowInfoDTO.class, byId.get()));
    }

    @Test
    void testRedis(){
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        redisTemplate.delete("s");
        opsForList.leftPushAll("s", list);
        System.out.println(opsForList.size("s"));
        System.out.println(opsForList.range("s", 0, opsForList.size("s") - 1));
    }
}
