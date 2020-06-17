package com.brainboom.matterwiki4jboot.services.impl;

import com.brainboom.matterwiki4jboot.entity.Topics;
import com.brainboom.matterwiki4jboot.repository.TopicsRepository;
import com.brainboom.matterwiki4jboot.dto.TopicsDto;
import com.brainboom.matterwiki4jboot.services.TopicsService;
import com.brainboom.matterwiki4jboot.services.exception.DefaultTopicNotDeleteException;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class TopicsServiceImpl implements TopicsService {

    @Autowired
    private TopicsRepository topicsRepository;


    /**
     * Get all topics with no pages
     *
     * @return TopicsDto
     */
    @Override
    public List<TopicsDto> GetAllTopicsNoPages() {
        List<Topics> topicsList = topicsRepository.findAll();
        List<TopicsDto> topicsDtoList = TopicsDto.convertFromTopicsList(topicsList);
        return topicsDtoList;
    }


    /**
     * Get  topics by id
     *
     * @param id
     * @return TopicsDto
     */
    @Override
    public TopicsDto GetTopicsById(int id) {
        Topics topics = topicsRepository.findTopicsById(id);
        TopicsDto topicsDto = TopicsDto.convertFromTopics(topics);
        return topicsDto;
    }


    /**
     * Update topics by id
     *
     * @param topicsDto
     * @return TopicsDto
     */
    @Override
    public TopicsDto UpdateTopics(TopicsDto topicsDto) throws UniqueNameException {
        String tpname = topicsDto.getName();
        if (topicsRepository.existsByName(tpname)) {
            throw new UniqueNameException("Topic " + tpname + " exists!");
        }
        Topics topics = topicsDto.convertToTopics();
        topicsRepository.saveAndFlush(topics);
        TopicsDto topicsDtoReturn = GetTopicsById(topicsDto.getId());
        return topicsDtoReturn;
    }


    /**
     * Insert a new topics
     *
     * @param topicsDto
     */
    @Override
    public TopicsDto InsertTopics(TopicsDto topicsDto) throws UniqueNameException {

        String tpname = topicsDto.getName();
        if (topicsRepository.existsByName(tpname)) {
            throw new UniqueNameException("Topic " + tpname + " exists!");
        }

        Topics topics = topicsDto.convertToTopics();
        topicsRepository.saveAndFlush(topics);
        Topics topicsReturn = topicsRepository.findTopicsByNameAndDescription(topicsDto.getName(), topicsDto.getDescription());
        TopicsDto topicsDtoReturn = TopicsDto.convertFromTopics(topicsReturn);
        return topicsDtoReturn;
    }


    /**
     * Delete topics by id
     *
     * @param id
     */
    @Override
    public void DeleteTopicsById(int id) throws Exception {
        if (id == 1) {
            throw new DefaultTopicNotDeleteException();
        }
        topicsRepository.deleteById(id);
    }


}
