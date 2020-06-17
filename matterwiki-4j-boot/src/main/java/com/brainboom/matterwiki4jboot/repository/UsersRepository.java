package com.brainboom.matterwiki4jboot.repository;

import com.brainboom.matterwiki4jboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findUsersById(int Id);

    Users findUsersByNameAndEmailAndAbout(String name,String email,String about);

    boolean existsByName(String name);

    boolean existsByEmail(String email);


    boolean existsByNameAndIdNotLike(String name,int id);

    boolean existsByEmailAndIdNotLike(String email,int id);


    boolean existsById(int id);


    Users findUsersByEmail(String email);

}
