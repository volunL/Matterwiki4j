package com.brainboom.matterwiki4jboot.services.impl;


import com.brainboom.matterwiki4jboot.dto.ArticlesDto;
import com.brainboom.matterwiki4jboot.entity.Archives;
import com.brainboom.matterwiki4jboot.entity.Articles;
import com.brainboom.matterwiki4jboot.entity.Topics;
import com.brainboom.matterwiki4jboot.repository.ArchivesRepository;
import com.brainboom.matterwiki4jboot.repository.ArticlesRepository;
import com.brainboom.matterwiki4jboot.services.ArticlesService;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl implements ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private ArchivesRepository archivesRepository;


    @Override
    public ArticlesDto AddArticle(ArticlesDto articlesDto) throws UniqueNameException {
        String title = articlesDto.getTitle();
        if (articlesRepository.existsByTitleAndTopicsByTopicId(title, new Topics(articlesDto.getTopic_id()))) {
            throw new UniqueNameException("Title " + title + " has been used in this topic!");
        }

        Articles articles = articlesDto.convertToArticles();
        Archives archivesToSave = new Archives(articles, articles.getTitle(), articles.getBody(), articles.getWhatChanged(), articles.getUsersByUserId());
        articlesRepository.saveAndFlush(articles);
        archivesRepository.saveAndFlush(archivesToSave);

        Articles articlesReturn = articlesRepository.findArticlesByTitleAndTopicsByTopicId(title, new Topics(articlesDto.getTopic_id()));
        ArticlesDto articlesDtoReturn = ArticlesDto.convertFromArticles(articlesReturn);
        return articlesDtoReturn;
    }

    @Override
    public void DeleteArticleById(int id) {
        articlesRepository.deleteById(id);
    }

    @Override
    public ArticlesDto UpdateArticleById(ArticlesDto articlesDto) throws UniqueNameException {
        String title = articlesDto.getTitle();
        if (articlesRepository.existsByTitleAndTopicsByTopicIdAndIdNotLike(title, new Topics(articlesDto.getTopic_id()),articlesDto.getId())) {
            throw new UniqueNameException("Title " + title + " has been used in this topic!");
        }
        Articles articles = articlesDto.convertToArticles();
        Archives archivesToSave = new Archives(articles, articles.getTitle(), articles.getBody(), articles.getWhatChanged(), articles.getUsersByUserId());
        articlesRepository.saveAndFlush(articles);
        archivesRepository.saveAndFlush(archivesToSave);
        Articles articlesReturn = articlesRepository.findArticlesById(articlesDto.getId());
        ArticlesDto articlesDtoReturn = ArticlesDto.convertFromArticles(articlesReturn);
        return articlesDtoReturn;
    }

    @Override
    public ArticlesDto GetArticleById(int id) {
        Articles articlesReturn = articlesRepository.findArticlesById(id);
        ArticlesDto articlesDto = ArticlesDto.convertFromArticles(articlesReturn);
        return articlesDto;
    }

    @Override
    public List<ArticlesDto> GetArticlesByTopicId(int topid) {
        List<Articles> articlesList = articlesRepository.findAllByTopicsByTopicIdOrderByUpdatedAtDesc(new Topics(topid));
        List<ArticlesDto> articlesDtoList = ArticlesDto.convertFromArticlesList(articlesList);
        return articlesDtoList;
    }


    @Override
    public List<ArticlesDto> GetAllArticleNoPages() {
        List<Articles> articlesList = articlesRepository.findAll();
        List<ArticlesDto> articlesDtoList = ArticlesDto.convertFromArticlesList(articlesList);
        return articlesDtoList;
    }

    @Override
    public List<ArticlesDto> SearchArticlesByTitleOrBody(String searchStr) {
        searchStr = "%"+searchStr+"%";
        List<Articles> articlesList = articlesRepository.findAllByTitleLikeOrBodyLike(searchStr, searchStr);
        List<ArticlesDto> articlesDtoList = ArticlesDto.convertFromArticlesList(articlesList);
        return articlesDtoList;
    }


}
