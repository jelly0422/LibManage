package com.library.manage.dao;

import com.library.manage.model.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.connection.stream.StreamInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FavorDao extends JpaRepository<Favor, Integer> {

    List<Favor> findByBelongstu(String belongstu);

    void deleteFavorByBelongstuAndISBN(String belongstu, String ISBN);

    Optional<Favor> findFavorByBelongstuAndISBN(String belongstu, String ISBN);

    @Query(value = "select category",nativeQuery = true)
    List<Map<String, Integer>> getStuFavorCount(String stuno);

    boolean existsByBelongstuAndISBN(String belongstu, String ISBN);

    @Query(value = "SELECT category as 'hobby', COUNT(*) as 'times'\n" +
            "FROM book WHERE ISBN=ANY(SELECT ISBN FROM favor WHERE belong_stu=?1) \n" +
            "GROUP BY category ORDER BY 'times' DESC ", nativeQuery = true)
    List<Map<String, Object>> getFavorCategory(String stuno);
}
