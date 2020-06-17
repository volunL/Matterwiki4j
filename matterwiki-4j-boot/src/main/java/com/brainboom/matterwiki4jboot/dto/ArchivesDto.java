package com.brainboom.matterwiki4jboot.dto;

import com.brainboom.matterwiki4jboot.entity.Archives;
import com.brainboom.matterwiki4jboot.entity.Articles;
import com.brainboom.matterwiki4jboot.entity.Users;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class ArchivesDto implements Serializable {

    private int id;
    private int article_id;
    private String title;
    private String body;
    private Date updated_at;
    private Integer user_id;
    private String what_changed;


    private UsersDto user;
    private ArticlesDto article;




    /**
     * ArchivesDto to Archives
     * @return archives
     * */
    public Archives convertToArchives() {
        Archives archives = new Archives();
        archives.setId(this.id);
        archives.setArticlesByArticleId(new Articles(this.article_id));
        //archives.setArticleId(this.article_id);
        archives.setTitle(this.title);
        archives.setBody(this.body);
        archives.setUsersByUserId(new Users(this.user_id));
        //archives.setUserId(this.user_id);
        archives.setWhatChanged(what_changed);
        return archives;
    }



    /**
     *
     *  ArchivesDto List to Archives List
     * @param archivesDtoList
     * @return archivesList
     * */
    public static List<Archives> convertToArchivesList(List<ArchivesDto> archivesDtoList){
        List<Archives> archivesList = new ArrayList<>();
        for(ArchivesDto archivesDto:archivesDtoList){
            archivesList.add(archivesDto.convertToArchives());
        }
        return archivesList;
    }






    /**
     *
     * Archives to ArchivesDto
     * @param archives
     * @return archivesDto
     * */
    public static ArchivesDto convertFromArchives(Archives archives) {
        ArchivesDto archivesDto = new ArchivesDto();
        archivesDto.setId(archives.getId());
        archivesDto.setArticle_id(archives.getArticlesByArticleId().getId());
        archivesDto.setTitle(archives.getTitle());
        archivesDto.setBody(archives.getBody());
        archivesDto.setUpdated_at(archives.getUpdatedAt());
        archivesDto.setUser_id(archives.getUsersByUserId().getId());
        archivesDto.setWhat_changed(archives.getWhatChanged());
        archivesDto.setArticle(ArticlesDto.convertFromArticles(archives.getArticlesByArticleId()));
        archivesDto.setUser(UsersDto.convertFromUsers(archives.getUsersByUserId()));
        return archivesDto;
    }



    /**
     *
     * Archives List to ArchivesDto List
     * @param archivesList
     * @return archivesDtoList
     * */
    public static List<ArchivesDto> convertFromArchivesList(List<Archives> archivesList) {

        List<ArchivesDto> archivesDtoList =  new ArrayList<>();
        for(Archives al:archivesList){
            ArchivesDto archivesDtoitem = convertFromArchives(al);
            archivesDtoList.add(archivesDtoitem);
        }
        return archivesDtoList;
    }





}
