package com.brainboom.matterwiki4jboot.repository;

import com.brainboom.matterwiki4jboot.entity.KnexMigrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KnexMigrationsRepository extends JpaRepository<KnexMigrations,Integer> {
}
