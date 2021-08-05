package com.library.manage.service;

import com.library.manage.model.entity.Book;
import com.library.manage.model.param.BookParam;
import com.library.manage.model.vo.BookVO;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public interface BookService {

    List<BookVO> getAllBook(String stuno);

    BookVO getBook(String ISBN);

    String updateBook(BookParam bookParam);

    List<BookVO> getHotBooks();

    List<BookVO> getStuRecommend(String stuno);

    Book addBook(BookParam bookParam);

    String deleteBooks(String ISBNs);

    Book getNormalBook(String ISBN);

    void save(Book book);

    void updateBorrow(String ISBN);

    void updateRet(String ISBN);

    boolean stockIsEnough(String ISBN);

    List<String> getISBNByCategory(String category);

    String uploadImage(MultipartFile file, String isbn);
}
