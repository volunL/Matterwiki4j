package com.brainboom.matterwiki4jboot.dto;

import com.brainboom.matterwiki4jboot.entity.Articles;
import com.brainboom.matterwiki4jboot.entity.Topics;
import com.brainboom.matterwiki4jboot.entity.Users;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class ArticlesDto implements Serializable {


    private int id;
    private String title;
    private String body;
    private Date created_at;
    private Date updated_at;
    private Integer topic_id;
    private Integer user_id;
    private String what_changed;

    private TopicsDto topic;
    private UsersDto user;


    public ArticlesDto() {
    }

    public ArticlesDto(int id, String title, String body, int topic_id, int user_id) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.topic_id = topic_id;
        this.user_id = user_id;
    }

    public ArticlesDto(String title, String body, int topic_id, int user_id) {
        this.title = title;
        this.body = body;
        this.topic_id = topic_id;
        this.user_id = user_id;
    }

    public ArticlesDto(int id, String title, String body, int topic_id, int user_id, String what_changed) {
        this.id=id;
        this.title = title;
        this.body = body;
        this.topic_id = topic_id;
        this.user_id = user_id;
        this.what_changed = what_changed;

    }


    /**
     * ArticlesDto to Articles
     *
     * @return articles
     */
    public Articles convertToArticles() {
        Articles articles = new Articles();
        articles.setId(this.id);
        articles.setTitle(this.title);
        articles.setBody(this.body);
        articles.setUsersByUserId(new Users(this.user_id));
        articles.setTopicsByTopicId(new Topics(this.topic_id));
        // articles.setTopicId(this.topic_id);
        // articles.setUserId(this.user_id);
        articles.setWhatChanged(this.what_changed);
        return articles;
    }


    /**
     * ArticlesDto List to Articles List
     *
     * @param articlesDtoList
     * @return articlesList
     */
    public static List<Articles> convertToArticlesList(List<ArticlesDto> articlesDtoList) {

        List<Articles> articlesList = new ArrayList<>();

        for (ArticlesDto articlesDto : articlesDtoList) {
            articlesList.add(articlesDto.convertToArticles());
        }
        return articlesList;
    }


    /**
     * Articles to ArticlesDto
     *
     * @param articles
     * @return articlesDto
     */
    public static ArticlesDto convertFromArticles(Articles articles) {
        ArticlesDto articlesDto = new ArticlesDto();
        articlesDto.setId(articles.getId());
        articlesDto.setTitle(articles.getTitle());
        articlesDto.setBody(articles.getBody());
        articlesDto.setCreated_at(articles.getCreatedAt());
        articlesDto.setUpdated_at(articles.getUpdatedAt());
        articlesDto.setTopic_id(articles.getTopicsByTopicId().getId());
        articlesDto.setUser_id(articles.getUsersByUserId().getId());
        // articlesDto.setTopic_id(articles.getTopicId());
        //  articlesDto.setUser_id(articles.getUserId());
        articlesDto.setWhat_changed(articles.getWhatChanged());

        articlesDto.setUser(UsersDto.convertFromUsers(articles.getUsersByUserId()));
        articlesDto.setTopic(
                TopicsDto.convertFromTopics(articles.getTopicsByTopicId())
        );
        return articlesDto;

    }


    /**
     * Articles List to    ArticlesDto List
     *
     * @param articlesList
     * @return articlesDtoList
     */
    public static List<ArticlesDto> convertFromArticlesList(List<Articles> articlesList) {

        List<ArticlesDto> articlesDtoList = new ArrayList<>();

        for (Articles articles : articlesList) {
            ArticlesDto articlesDtoItem = convertFromArticles(articles);
            articlesDtoList.add(articlesDtoItem);
        }
        return articlesDtoList;
    }


}
