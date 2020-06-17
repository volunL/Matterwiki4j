package com.brainboom.matterwiki4jboot.services;


import com.brainboom.matterwiki4jboot.dto.ArticlesDto;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;

import java.util.List;


public interface ArticlesService {

    ArticlesDto AddArticle(ArticlesDto articlesDto) throws UniqueNameException;

    void  DeleteArticleById(int id);

    ArticlesDto UpdateArticleById(ArticlesDto articlesDto) throws UniqueNameException;

    ArticlesDto GetArticleById(int id);

    List<ArticlesDto> GetArticlesByTopicId(int topid);

    List<ArticlesDto> GetAllArticleNoPages();

    List<ArticlesDto> SearchArticlesByTitleOrBody(String searchStr);





}
