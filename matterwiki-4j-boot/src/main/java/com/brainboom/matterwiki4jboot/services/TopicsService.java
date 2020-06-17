package com.brainboom.matterwiki4jboot.services;

import com.brainboom.matterwiki4jboot.dto.TopicsDto;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;

import java.util.List;

public interface TopicsService {


    /**
     * Get all topics with no pages
     *
     * @return List<TopicsDto>
     */
    List<TopicsDto> GetAllTopicsNoPages();

    /**
     * Get  topics by id
     *
     * @param id
     * @return TopicsDto
     */
    TopicsDto GetTopicsById(int id);


    /**
     * Update topics by id
     *
     * @param topicsDto
     * @return TopicsDto
     */
    TopicsDto UpdateTopics(TopicsDto topicsDto) throws UniqueNameException;


    /**
     * Insert a new topics
     *
     * @param topicsDto
     * @return TopicsDto
     */
    TopicsDto InsertTopics(TopicsDto topicsDto) throws  UniqueNameException;

    /**
     * Delete topics by id
     *
     * @param id
     */
     void DeleteTopicsById(int id) throws Exception;


}
