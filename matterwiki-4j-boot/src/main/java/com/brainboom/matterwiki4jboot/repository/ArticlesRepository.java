package com.brainboom.matterwiki4jboot.repository;


import com.brainboom.matterwiki4jboot.entity.Users;
import com.brainboom.matterwiki4jboot.entity.Articles;
import com.brainboom.matterwiki4jboot.entity.Topics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Integer> {

    Articles findArticlesById(int Id);

    Articles findArticlesByTitleAndTopicsByTopicId(String title, Topics topicsId);

    boolean existsByTitleAndTopicsByTopicIdAndIdNotLike(String title, Topics topicsId,int id);

    List<Articles> findAllByTopicsByTopicIdOrderByUpdatedAtDesc(Topics topics);

    Page<Articles> findAllByTopicsByTopicId(Topics topics, Pageable pageable);

    List<Articles> findAllByTitleLikeOrBodyLike(String title, String body);


    List<Articles> findAllByUsersByUserIdOrderByUpdatedAtDesc(Users users);

    Page<Articles> findAllByUsersByUserId(Users users, Pageable pageable);


    List<Articles> findAllByTitleLike(String Title);


    Page<Articles> findAllByTitleLike(String Title, Pageable pageable);


    Page<Articles> findAllByTitleLikeOrBodyLike(String Title, String Body, Pageable pageable);


    boolean existsByTitleAndTopicsByTopicId(String title, Topics topics);
}
