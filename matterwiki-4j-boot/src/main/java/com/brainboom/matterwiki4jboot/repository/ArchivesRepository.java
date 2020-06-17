package com.brainboom.matterwiki4jboot.repository;

import com.brainboom.matterwiki4jboot.entity.Archives;
import com.brainboom.matterwiki4jboot.entity.Articles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivesRepository extends JpaRepository<Archives, Integer> {


    Archives findArchivesById(int Id);

    List<Archives> findAllByArticlesByArticleIdOrderByUpdatedAtDesc(Articles articles);

//    Page<Archives> findAllByArticlesByArticleId(Articles articles, Pageable pageable);
//
//    List<Archives> findAllByArticlesByArticleIdOrderByUpdatedAtDesc(Articles articles);
//
//    Page<Archives> findAllByUsersByUserId(Users users, Pageable pageable);
//
//    List<Archives> findAllByUsersByUserIdOrderByUpdatedAtDesc(Users users);


}
