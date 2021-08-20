package com.library.manage.service.impl;

import com.jcraft.jsch.SftpException;
import com.library.manage.dao.BookDao;
import com.library.manage.model.dto.CategoryDTO;
import com.library.manage.model.entity.Book;
import com.library.manage.model.param.BookParam;
import com.library.manage.model.vo.BookVO;
import com.library.manage.model.vo.FavorVO;
import com.library.manage.service.*;
import com.library.manage.util.BeanUtil;
import com.library.manage.util.Sftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    @Autowired
    FavorService favorService;
    @Autowired
    BorrowInfoService borrowInfoService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    /**
     * 获取所有书籍以及书籍的相关信息
     * @param stuno 当前登录的学生学号
     * @return 书籍以及相关信息包装对象集合
     */
    @Override
    public List<BookVO> getAllBook(String stuno) {
        List<Book> all = bookDao.findAll();//从数据库获取书籍基本信息
        List<BookVO> bookVOS = new ArrayList<>();//创建包装对象链表
        //遍历书籍基本信息，添加到包装对象中
        all.forEach(book -> {
            BookVO bookVO = new BookVO();
            bookVO.setBook(book);
            bookVOS.add(bookVO);
        });

        //遍历书籍基本信息，从数据库获取其他相关信息添加到包装对象中
        for (BookVO b:
             bookVOS) {
            String isbn = b.getBook().getISBN();
            Integer category = b.getBook().getCategory();
            b.setIsFavor(favorService.isFavor(isbn, stuno));
            b.setComment(commentService.getBookComment(isbn));
            b.setCategory(categoryService.getCategory(category));
            b.setBorrowStatus(borrowInfoService.getBorrowStatus(stuno, isbn));
        }

        return bookVOS;
    }

    /**
     * 获取一本书籍
     * @param ISBN 书籍码
     * @return 书籍
     */
    @Override
    public BookVO getBook(String ISBN) {

        BookVO bookVO = null;

        Optional<Book> book = bookDao.findByISBN(ISBN);

        bookVO = new BookVO();
        bookVO.setBook(book.orElse(null));
        bookVO.setCategory(BeanUtil.entityToDTO(CategoryDTO.class, categoryService.getCategory(book.get().getCategory())));
        bookVO.setComment(commentService.getBookComment(ISBN));
        return bookVO;
    }


    @Transactional
    @Override
    public String updateBook(BookParam bookParam) {

        Book book = BeanUtil.transformFrom(bookParam, Book.class);
        bookDao.saveAndFlush(book);

        return "修改成功";
    }

    @Override
    public List<BookVO> getHotBooks() {
        List<BookVO> hot = new ArrayList<>();
        List<Map<String, Object>> scoreAndISBN = bookDao.findScoreAndISBN();
        Iterator<Map<String, Object>> iterator = scoreAndISBN.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            hot.add(getBook((String) iterator.next().get("ISBN")));
            count++;
            if (count == 5) {
                break;
            }
        }
        return hot;
    }

    @Override
    public List<BookVO> getStuRecommend(String stuno) {
        FavorVO favorVO = favorService.getFavorVO(stuno);
        List<BookVO> books = favorVO.getBooks();
        Iterator<BookVO> iterator = books.iterator();
        while(iterator.hasNext()){
            iterator.next().getCategory().getId();
        }
        return null;
    }

    @Transactional
    @Override
    public Book addBook(BookParam bookParam) {


        return bookDao.saveAndFlush(BeanUtil.transformFrom(bookParam, Book.class));
    }

    @Transactional
    @Override
    public String deleteBooks(Integer id) {
        bookDao.deleteById(id);
        return "删除成功";
    }

    @Override
    public Book getNormalBook(String ISBN) {
        return bookDao.findByISBN(ISBN).orElse(null);
    }

    @Transactional
    @Override
    public void save(Book book) {
        bookDao.saveAndFlush(book);
    }

    @Transactional
    @Override
    public void updateBorrow(String ISBN) {
        Optional<Book> byId = bookDao.findByISBN(ISBN);
        byId.ifPresent(book -> {
            book.setStock(book.getStock() - 1);
            book.setBorrowednum(book.getBorrowednum() + 1);
        });
    }

    @Transactional
    @Override
    public void updateRet(String ISBN) {
        bookDao.findByISBN(ISBN).ifPresent(book -> {
            book.setStock(book.getStock() + 1);
            book.setBorrowednum(book.getBorrowednum() - 1);
        });
    }

    @Transactional
    @Override
    public boolean stockIsEnough(String ISBN) {
        return bookDao.findByISBN(ISBN).get().getStock() > 0;
    }

    @Override
    public List<String> getISBNByCategory(String category) {
        return bookDao.getISBNByCategory(category);
    }

    @Override
    public String uploadImage(MultipartFile file, String isbn) {
        Sftp sftp = new Sftp("root", "Chen3119", "8.135.28.126", 22);
        sftp.login();

        try{
            sftp.upload("/www/wwwroot/8.135.28.126/lib", isbn + ".jpg", file.getInputStream());
        } catch (SftpException | IOException e) {
            System.out.println("上传失败");
            e.printStackTrace();
        }
        return "上传成功";
    }

}
