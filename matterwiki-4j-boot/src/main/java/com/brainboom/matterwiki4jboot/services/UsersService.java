package com.brainboom.matterwiki4jboot.services;


import com.brainboom.matterwiki4jboot.dto.UsersDto;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;

import java.util.HashMap;
import java.util.List;


public interface UsersService {


    UsersDto AddUser(UsersDto usersDto) throws Exception;


    void DeleteUserById(int i) throws Exception;


    UsersDto UpdateUserById(UsersDto usersDto) throws UniqueNameException;


    List<UsersDto> GetAllUsersNoPages();


    UsersDto GetUsersById(int id);

    UsersDto setup(UsersDto usersDto) throws Exception;

    HashMap<String, Object> authenticate(UsersDto usersDto) throws  Exception;



}
