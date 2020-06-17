package com.brainboom.matterwiki4jboot.repository;

import com.brainboom.matterwiki4jboot.entity.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicsRepository extends JpaRepository<Topics, Integer> {


    Topics findTopicsById(int Id);

    Topics findTopicsByNameAndDescription(String name,String description);

    boolean existsByName(String name);





}
