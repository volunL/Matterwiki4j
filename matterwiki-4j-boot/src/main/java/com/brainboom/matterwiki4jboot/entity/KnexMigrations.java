package com.brainboom.matterwiki4jboot.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "knex_migrations")
public class KnexMigrations {
    private int id;
    private String name;
    private Integer batch;
    private Date migrationTime;


    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name = "batch")
    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    
    @Column(name = "migration_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getMigrationTime() {
        return migrationTime;
    }

    public void setMigrationTime(Date migrationTime) {
        this.migrationTime = migrationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnexMigrations that = (KnexMigrations) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(batch, that.batch) &&
                Objects.equals(migrationTime, that.migrationTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, batch, migrationTime);
    }
}
