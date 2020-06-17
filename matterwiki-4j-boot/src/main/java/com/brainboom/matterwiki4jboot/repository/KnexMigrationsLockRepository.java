package com.brainboom.matterwiki4jboot.repository;

import com.brainboom.matterwiki4jboot.entity.KnexMigrationsLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KnexMigrationsLockRepository extends JpaRepository<KnexMigrationsLock,Integer> {
}
