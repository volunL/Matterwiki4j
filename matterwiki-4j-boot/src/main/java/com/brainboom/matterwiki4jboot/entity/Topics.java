package com.brainboom.matterwiki4jboot.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;


@Entity
public class Topics {
    private int id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Collection<Articles> articlesById;

    public Topics(int id) {
        this.id=id;
    }





    public Topics() {
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


    @Column(name = "name",unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    @Generated(GenerationTime.INSERT)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topics topics = (Topics) o;
        return id == topics.id &&
                Objects.equals(name, topics.name) &&
                Objects.equals(description, topics.description) &&
                Objects.equals(createdAt, topics.createdAt) &&
                Objects.equals(updatedAt, topics.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "topicsByTopicId")
    public Collection<Articles> getArticlesById() {
        return articlesById;
    }

    public void setArticlesById(Collection<Articles> articlesById) {
        this.articlesById = articlesById;
    }
}
