package com.library.manage.task;

import com.library.manage.model.vo.BookVO;
import com.library.manage.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 * @author jelly
 */
@Component
@EnableScheduling
public class StaticScheduleTask implements ApplicationRunner {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private BookService bookService;

    /**
     * 定时更新热门书籍缓存
     */
    @Scheduled(cron = "0 0 * * * *")
    private void task(){
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        List<BookVO> hotBooks = bookService.getHotBooks();
        if (redisTemplate.hasKey("hot")){
            redisTemplate.delete("hot");
        }
        hotBooks.forEach(bookVO -> opsForList.rightPush("hot", bookVO));
        System.out.println("done scheduled task");
    }

    /**
     * 应用启动时进行缓存预热
     * @param args canshu
     * @throws Exception yichang
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        task();
    }
}

