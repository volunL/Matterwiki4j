package com.brainboom.matterwiki4jboot.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "knex_migrations_lock")
public class KnexMigrationsLock {

    private int isLocked;

    @Id
    @Column(name = "is_locked",columnDefinition = "int")
    public int getLocked() {
        return isLocked;
    }

    public void setLocked(int locked) {
        isLocked = locked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnexMigrationsLock that = (KnexMigrationsLock) o;
        return Objects.equals(isLocked, that.isLocked);
    }

    @Override
    public int hashCode() {

        return Objects.hash(isLocked);
    }
}
