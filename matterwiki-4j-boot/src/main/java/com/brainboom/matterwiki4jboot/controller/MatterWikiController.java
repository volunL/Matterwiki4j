package com.brainboom.matterwiki4jboot.controller;


import com.brainboom.matterwiki4jboot.services.ArchivesService;
import com.brainboom.matterwiki4jboot.services.TopicsService;
import com.brainboom.matterwiki4jboot.dto.ArchivesDto;
import com.brainboom.matterwiki4jboot.dto.ArticlesDto;
import com.brainboom.matterwiki4jboot.dto.TopicsDto;
import com.brainboom.matterwiki4jboot.dto.UsersDto;
import com.brainboom.matterwiki4jboot.response.CommResponse;
import com.brainboom.matterwiki4jboot.error.Code;
import com.brainboom.matterwiki4jboot.services.ArticlesService;
import com.brainboom.matterwiki4jboot.services.UsersService;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api")

public class MatterWikiController {

    @Autowired
    TopicsService topicsService;

    @Autowired
    UsersService usersService;


    @Autowired
    ArticlesService articlesService;

    @Autowired
    ArchivesService archivesService;


    /**
     * Get All Topics
     */
    @ApiOperation(value = "Get All Topics")
    @GetMapping("/topics")
    public CommResponse GetTopicsAll() {
        List<TopicsDto> topicsDtoList = topicsService.GetAllTopicsNoPages();
        return CommResponse.success(topicsDtoList, Code.B123.getCode());
    }


    /**
     * Get Topics By Id
     *
     * @param id
     */
    @ApiOperation(value = "Get Topics By Id")
    @GetMapping("/topics/{id}")
    public CommResponse GetTopicsById(@PathVariable("id") int id) {
        TopicsDto topicsDto = topicsService.GetTopicsById(id);
        return CommResponse.success(topicsDto, Code.B123.getCode());
    }


    /**
     * Get articles By topic id
     *
     * @param id
     */
    @ApiOperation(value = "Get articles By topic id")
    @GetMapping("/topic/{id}/articles")
    public CommResponse GetArticlesByTopicId(@PathVariable("id") int id) {
        List<ArticlesDto> articlesDtoList = articlesService.GetArticlesByTopicId(id);
        return CommResponse.success(articlesDtoList, Code.B105.getCode());
    }

    /**
     * Get article By id
     *
     * @param id
     */
    @ApiOperation(value = "Get article By id")
    @GetMapping("/articles/{id}")
    public CommResponse GetArticlesById(@PathVariable("id") int id) {
        ArticlesDto articlesDto = articlesService.GetArticleById(id);
        return CommResponse.success(articlesDto, Code.B105.getCode());
    }

    /**
     * Update article By id
     *
     * @param id
     * @param title
     * @param body
     * @param topic_id
     * @param user_id
     */
    @ApiOperation(value = "Update article By id")
    @PutMapping("/articles")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public CommResponse UpdateArticlesById(@RequestParam("id") int id,
                                           @RequestParam("title") String title,
                                           @RequestParam("body") String body,
                                           @RequestParam("topic_id") int topic_id,
                                           @RequestParam("user_id") int user_id,
                                           @RequestParam("what_changed") String what_changed) throws UniqueNameException {
        ArticlesDto articlesDto = articlesService.UpdateArticleById(new ArticlesDto(id, title, body, topic_id, user_id, what_changed));
        return CommResponse.success(articlesDto, Code.B107.getCode());
    }


    /**
     * Add article
     *
     * @param title
     * @param body
     * @param topic_id
     * @param user_id
     */
    @ApiOperation("Add article")
    @PostMapping("/articles")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public CommResponse AddArticles(@RequestParam("title") String title,
                                    @RequestParam("body") String body,
                                    @RequestParam("topic_id") int topic_id,
                                    @RequestParam("user_id") int user_id) throws UniqueNameException {
        ArticlesDto articlesDto = articlesService.AddArticle(new ArticlesDto(title, body, topic_id, user_id));
        return CommResponse.success(articlesDto, Code.B103.getCode());
    }


    /**
     * Get Article's History By Article Id
     *
     * @param id
     */
    @ApiOperation("Get Article's History By Article Id")
    @GetMapping("/articles/{id}/history")
    public CommResponse GetArticlesHistoryByArticleId(@PathVariable("id") int id) {
        List<ArchivesDto> archivesDtoList = archivesService.GetAllArchivesByArticleId(id);
        return CommResponse.success(archivesDtoList, Code.B115.getCode());
    }



    @GetMapping("/archives/{id}")
    public CommResponse GetArchiveById(@PathVariable("id") int id){

        ArchivesDto archivesDto = archivesService.GetArchivesById(id);
        return CommResponse.success(archivesDto,Code.B113.getCode());

    }




    /**
     * Get all  Articles
     *
     *
     */
    @ApiOperation("Get all  Articles")
    @GetMapping("/articles")
    public CommResponse GetAllArticles() {
        List<ArticlesDto> articlesDtoList = articlesService.GetAllArticleNoPages();
        return CommResponse.success(articlesDtoList, Code.B115.getCode());
    }






    /**
     * This is a GET endpoint that takes IDs of two articles as parameters.
     * It then returns both the article which could then be compared with each other
     * through diffing which will be done on the front-end.
     * The IDs params names are:
     * article1: id of the first article
     * article2: id of the second article
     * The article with ID article1 is the first object in the Data array.
     * The article with ID article2 is the second object in the Data array.
     * The error key in the returning object is a boolen which is false if there is no error and true otherwise
     *
     * @param article1
     * @param article2
     */
    @ApiOperation("Get two articles by ID and return both the article which could then be compared with each other through diffing which will be done on the front-end")
    @GetMapping("/articles/compare")
    public CommResponse CompareArticles(@RequestParam("article1") int article1,
                                        @RequestParam("article2") int article2) {
        ArticlesDto articlesDto1 = articlesService.GetArticleById(article1);
        ArticlesDto articlesDto2 = articlesService.GetArticleById(article2);
        ArticlesDto[] articlesDtos = {articlesDto1, articlesDto2};
        return CommResponse.success(articlesDtos, Code.B111.getCode());
    }


    /**
     * This is a GET enpoint which takes the search query as a URL param
     * Runs the search query and returns matching articles in the data key in the
     * response object.
     * The endpoint only searches article titles as of now.
     */
    @GetMapping("/search")
    public CommResponse search(@RequestParam("query") String query) {
        List<ArticlesDto> articlesDtoList = articlesService.SearchArticlesByTitleOrBody(query);
        return CommResponse.success(articlesDtoList, Code.B131.getCode());
    }


    @PostMapping("/authenticate")
    public CommResponse authenticate(@RequestParam("email") String email, @RequestParam("password") String password) throws Exception {

        HashMap hashMap = usersService.authenticate(new UsersDto(email, password));

        return CommResponse.success(hashMap, Code.B118.getCode());
    }


}
