package com.brainboom.matterwiki4jboot.dto;


import com.brainboom.matterwiki4jboot.entity.Users;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data

public class UsersDto implements Serializable {


    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
    private Date created_at;
    private Date updated_at;


    public UsersDto() {
    }

    public UsersDto(String name, String email, String password, String about) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public UsersDto(int id, String name, String email, String password, String about) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;

        this.about = about;
    }

    public UsersDto(String email, String password) {
        this.email =email;
        this.password =password;
    }


    /**
     * UsersDto to Users
     *
     * @return users
     */
    public Users convertToUsers() {
        Users users = new Users();
        users.setId(this.id);
        users.setName(this.name);
        users.setEmail(this.email);
        users.setPassword(this.password);
        users.setAbout(this.about);
        return users;
    }


    /**
     * UsersDto List to Users List
     *
     * @param usersDtoList
     * @return usersList
     */

    public static List<Users> convertToUsersList(List<UsersDto> usersDtoList) {
        List<Users> usersList = new ArrayList<>();

        for (UsersDto usersDto : usersDtoList) {
            usersList.add(usersDto.convertToUsers());
        }

        return usersList;


    }


    /**
     * Users to UsersDto
     *
     * @param users
     * @return users
     */
    public static UsersDto convertFromUsers(Users users) {
        UsersDto usersDto = new UsersDto();
        usersDto.setId(users.getId());
        usersDto.setName(users.getName());
        usersDto.setEmail(users.getEmail());
        //do not show password to the front
        //usersDto.setPassword(users.getPassword());
        usersDto.setAbout(users.getAbout());
        usersDto.setCreated_at(users.getCreatedAt());
        usersDto.setUpdated_at(users.getUpdatedAt());
        return usersDto;
    }


    /**
     * Users List to UsersDto List
     *
     * @param usersList
     * @return usersDtoList
     */
    public static List<UsersDto> convertFromUsersList(List<Users> usersList) {
        List<UsersDto> usersDtoList = new ArrayList<>();
        for (Users users : usersList) {
            UsersDto usersDtoItem = convertFromUsers(users);
            usersDtoList.add(usersDtoItem);
        }

        return usersDtoList;


    }


}
