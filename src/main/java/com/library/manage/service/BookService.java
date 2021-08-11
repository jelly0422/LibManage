package com.library.manage.service;

import com.library.manage.model.entity.Book;
import com.library.manage.model.param.BookParam;
import com.library.manage.model.vo.BookVO;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author jelly
 */
@Service
public interface BookService {

    /**
     * 获取所有书籍信息
     * @param stuno 学号，用于判断当前学生用户对书籍的借阅情况和收藏情况
     * @return 返回书籍对应信息
     */
    List<BookVO> getAllBook(String stuno);

    /**
     * 通过ISBN码获取书籍信息
     * @param ISBN 书籍码
     * @return 书籍信息
     */
    BookVO getBook(String ISBN);

    /**
     * 更新书籍信息
     * @param bookParam 更新参数
     * @return 更新结果
     */
    String updateBook(BookParam bookParam);

    /**
     * 获取热门书籍
     * @return 热门书籍集合
     */
    List<BookVO> getHotBooks();

    /**
     * 获取学生用户个人推荐书籍
     * @param stuno 学号
     * @return 个人推荐书籍集合
     */
    List<BookVO> getStuRecommend(String stuno);

    /**
     * 添加书籍
     * @param bookParam 添加的书籍参数
     * @return 返回添加的书籍
     */
    Book addBook(BookParam bookParam);

    /**
     * 通过id删除书籍
     * @param ISBNs id
     * @return 删除结果
     */
    String deleteBooks(Integer ISBNs);

    /**
     * 获取书籍，不包含分类评论信息
     * @param ISBN 书籍ISBN码
     * @return 书籍
     */
    Book getNormalBook(String ISBN);

    /**
     * 保存书籍
     * @param book
     */
    void save(Book book);

    /**
     * 更新借阅信息
     * @param ISBN 书籍ISBN码
     */
    void updateBorrow(String ISBN);

    /**
     * 更新归还信息
     * @param ISBN ISBN码
     */
    void updateRet(String ISBN);

    /**
     * 判断书籍库存是否足够
     * @param ISBN 书籍ISBN
     * @return 库存是否足够
     */
    boolean stockIsEnough(String ISBN);

    /**
     * 通过分类获取书籍
     * @param category 分类信息
     * @return 书籍集合
     */
    List<String> getISBNByCategory(String category);

    /**
     * 上传书籍图片
     * @param file 图片文件
     * @param isbn isbn
     * @return 上传结果
     */
    String uploadImage(MultipartFile file, String isbn);
}
