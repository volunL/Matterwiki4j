package com.brainboom.matterwiki4jboot.entity;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
public class Users {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    private Date createdAt;
    private Date updatedAt;
    private Collection<Archives> archivesById;
    private Collection<Articles> articlesById;


    public Users() {
    }

    public Users(int id) {
        this.id = id;
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


    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Column(name = "about", nullable = false)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    @Column(name = "updated_at", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
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
        Users users = (Users) o;
        return id == users.id &&
                Objects.equals(name, users.name) &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password) &&
                Objects.equals(about, users.about) &&
                Objects.equals(createdAt, users.createdAt) &&
                Objects.equals(updatedAt, users.updatedAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, password, about, createdAt, updatedAt);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Archives> getArchivesById() {
        return archivesById;
    }

    public void setArchivesById(Collection<Archives> archivesById) {
        this.archivesById = archivesById;
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<Articles> getArticlesById() {
        return articlesById;
    }

    public void setArticlesById(Collection<Articles> articlesById) {
        this.articlesById = articlesById;
    }
}
