package com.brainboom.matterwiki4jboot.dto;


import com.brainboom.matterwiki4jboot.entity.Topics;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TopicsDto implements Serializable {


    private int id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;

    public TopicsDto(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TopicsDto() {
    }

    public TopicsDto(String name, String description) {
        this.description = description;
        this.name = name;
    }

    public TopicsDto(int id) {

        this.id=id;
    }


    /**
     * TopicsDto to Topics
     *
     * @return topics
     */
    public Topics convertToTopics() {
        Topics topics = new Topics();
        topics.setId(this.id);
        topics.setName(this.name);
        topics.setDescription(this.description);
        // topics.setCreatedAt(this.createdAt);
        //topics.setUpdatedAt(this.updatedAt);
        return topics;
    }


    /**
     * TopicsDto List to Topics List
     *
     * @param topicsDtoList
     * @return topicsList
     */
    public static List<Topics> convertFromTopicsDto(List<TopicsDto> topicsDtoList) {
        List<Topics> topicsList = new ArrayList<>();
        for (TopicsDto topicsDto : topicsDtoList) {
            topicsList.add(topicsDto.convertToTopics());
        }
        return topicsList;
    }


    /**
     * Topics to TopicsDto
     *
     * @param topics
     * @return topicsDto
     */
    public static TopicsDto convertFromTopics(Topics topics) {
        TopicsDto topicsDto = new TopicsDto();
        topicsDto.setId(topics.getId());
        topicsDto.setName(topics.getName());
        topicsDto.setDescription(topics.getDescription());
        topicsDto.setCreated_at(topics.getCreatedAt());
        topicsDto.setUpdated_at(topics.getUpdatedAt());
        return topicsDto;
    }


    /**
     * TopicsDto List to Topics List
     *
     * @param topicsList
     * @return topicsDtoList
     */
    public static List<TopicsDto> convertFromTopicsList(List<Topics> topicsList) {

        List<TopicsDto> topicsDtoList = new ArrayList<>();
        for (Topics topics : topicsList) {
            TopicsDto topicsDtoItem = convertFromTopics(topics);
            topicsDtoList.add(topicsDtoItem);
        }

        return topicsDtoList;

    }


}
