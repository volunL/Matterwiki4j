package com.brainboom.matterwiki4jboot.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "archives"
        // ,indexes = {@Index(columnList = "user_id",name = "archives_user_id_foreign"),
        // @Index(columnList = "article_id",name = "archives_article_id_foreign")}
)
public class Archives {
    private int id;
    //   private int articleId;
    private String title;
    private String body;
    private Date updatedAt;
    // private Integer userId;
    private String whatChanged;
    private Articles articlesByArticleId;
    private Users usersByUserId;


    public Archives() {
    }

    public Archives(Articles articles, String title, String body, String whatChanged, Users usersByUserId) {
        this.articlesByArticleId = articles;
        this.title = title;
        this.body = body;
        this.whatChanged = whatChanged;
        this.usersByUserId = usersByUserId;
    }


    @Column(name = "id", columnDefinition = "int unsigned auto_increment ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//    @Column(name = "article_id",columnDefinition = "int unsigned",nullable = false)
//    public int getArticleId() {
//        return articleId;
//    }
//
//    public void setArticleId(int articleId) {
//        this.articleId = articleId;
//    }


    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Column(name = "body", columnDefinition = "text", nullable = false)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    @Column(name = "updated_at", columnDefinition = "timestamp default CURRENT_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    @Generated(GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


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
        Archives archives = (Archives) o;
        return id == archives.id &&
                //   articleId == archives.articleId &&
                Objects.equals(title, archives.title) &&
                Objects.equals(body, archives.body) &&
                Objects.equals(updatedAt, archives.updatedAt) &&
                //    Objects.equals(userId, archives.userId) &&
                Objects.equals(whatChanged, archives.whatChanged);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id,
                //    articleId,
                title,
                body,
                updatedAt,
                //   userId,
                whatChanged);
    }

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false, columnDefinition = "int unsigned ", foreignKey = @ForeignKey(name = "archives_article_id_foreign", value = ConstraintMode.PROVIDER_DEFAULT))
    public Articles getArticlesByArticleId() {
        return articlesByArticleId;
    }

    public void setArticlesByArticleId(Articles articlesByArticleId) {
        this.articlesByArticleId = articlesByArticleId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, columnDefinition = "int unsigned", foreignKey = @ForeignKey(name = "archives_user_id_foreign", value = ConstraintMode.PROVIDER_DEFAULT))
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }
}
