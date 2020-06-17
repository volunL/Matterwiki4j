package com.brainboom.matterwiki4jboot.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "articles"
        //,indexes = {@Index(columnList = "user_id",name = "articles_user_id_foreign"),@Index(columnList = "topic_id",name = "articles_topic_id_foreign")}
)
public class Articles {
    private int id;
    private String title;
    private String body;
    private Date createdAt;
    private Date updatedAt;
   // private Integer topicId;
  //  private Integer userId;
    private String whatChanged;
    private Collection<Archives> archivesById;
    private Topics topicsByTopicId;
    private Users usersByUserId;


    public Articles(){}

    public Articles(int id){
        this.id=id;
    }





    @Column(name = "id",columnDefinition = "int unsigned auto_increment ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "title",nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
    @Column(name = "body",nullable = false,columnDefinition = "text")
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    
    @Column(name = "created_at",columnDefinition = "timestamp default CURRENT_TIMESTAMP",nullable = false,insertable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    
    @Column(name = "updated_at",columnDefinition = "timestamp default CURRENT_TIMESTAMP",nullable = false,insertable = false,updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.ALWAYS)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    
//    @Column(name = "topic_id",columnDefinition = "int unsigned")
//    public Integer getTopicId() {
//        return topicId;
//    }
//
//    public void setTopicId(Integer topicId) {
//        this.topicId = topicId;
//    }
//
//
//    @Column(name = "user_id",columnDefinition = "int unsigned")
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    
    @Column(name = "what_changed")
    public String getWhatChanged() {
        return whatChanged;
    }

    public void setWhatChanged(String whatChanged) {
        this.whatChanged = whatChanged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Articles articles = (Articles) o;
        return id == articles.id &&
                Objects.equals(title, articles.title) &&
                Objects.equals(body, articles.body) &&
                Objects.equals(createdAt, articles.createdAt) &&
                Objects.equals(updatedAt, articles.updatedAt) &&
              //  Objects.equals(topicId, articles.topicId) &&
              //  Objects.equals(userId, articles.userId) &&
                Objects.equals(whatChanged, articles.whatChanged);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, body, createdAt, updatedAt,
               // topicId, userId,
                whatChanged);
    }

    @OneToMany(mappedBy = "articlesByArticleId")
    public Collection<Archives> getArchivesById() {
        return archivesById;
    }

    public void setArchivesById(Collection<Archives> archivesById) {
        this.archivesById = archivesById;
    }

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id" ,columnDefinition = "int unsigned",nullable = false,foreignKey = @ForeignKey(name = "articles_topic_id_foreign"))
    public Topics getTopicsByTopicId() {
        return topicsByTopicId;
    }

    public void setTopicsByTopicId(Topics topicsByTopicId) {
        this.topicsByTopicId = topicsByTopicId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",columnDefinition = "int unsigned",nullable = false,foreignKey = @ForeignKey(name = "articles_user_id_foreign"))
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
