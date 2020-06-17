package com.brainboom.matterwiki4jboot.controller;


import com.brainboom.matterwiki4jboot.dto.TopicsDto;
import com.brainboom.matterwiki4jboot.dto.UsersDto;
import com.brainboom.matterwiki4jboot.error.Code;
import com.brainboom.matterwiki4jboot.services.TopicsService;
import com.brainboom.matterwiki4jboot.response.CommResponse;
import com.brainboom.matterwiki4jboot.services.ArticlesService;
import com.brainboom.matterwiki4jboot.services.UsersService;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * This controller contains all the apis which are accessible only to the admin.
 * The apis are
 * GET /users
 * POST /users
 * PUT  /users
 * DELETE  /users
 * POST /topics
 * PUT /topics
 * DELETE /topics
 * DELETE /articles
 *
 * @author volun
 * @version 1
 */

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    TopicsService topicsService;

    @Autowired
    UsersService usersService;


    @Autowired
    ArticlesService articlesService;


    /**
     * This endpoint takes the topic name and topic description from the request body.
     * It then saves those values in the database using the insert query.
     *
     * @param name
     * @param description
     */
    @ApiOperation(value = "Add a Topic", notes = "```\n" +
            "This endpoint takes the topic name and topic description from the request body.\n" +
            "     It then saves those values in the database using the insert query.")
    @PostMapping("/topics")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse AddTopics(@RequestParam("name") String name, @RequestParam("description") String description) throws UniqueNameException {
        TopicsDto topicsDto = new TopicsDto(name, description);
        TopicsDto topicsDtoResp = topicsService.InsertTopics(topicsDto);
        return CommResponse.success(topicsDtoResp, Code.B121.getCode());
    }


    /**
     * Takes the topic id, name, about, email and password.
     * It first finds the topic with the given ID and then updates its profile fields with the given values.
     * <p>
     * **Required Params:**
     * <p>
     * ```
     * id
     * <p>
     * name
     * <p>
     * description
     * <p>
     * (the above keys should be present in the request body)
     * <p>
     * Access Token
     *
     * @param id
     * @param name
     * @param description
     */
    @ApiOperation(value = "Update Topics By Id",
            notes = "```\n" +
                    "Takes the topic id, name, about, email and password.\n" +
                    "It first finds the topic with the given ID and then updates its profile fields with the given values.\n")
    @PutMapping("/topics")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse UpdateTopicsById(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("description") String description) throws UniqueNameException {
        TopicsDto topicsDto = new TopicsDto(id, name, description);
        TopicsDto topicsDtoResp = topicsService.UpdateTopics(topicsDto);
        return CommResponse.success(topicsDtoResp, Code.B125.getCode());
    }


    @DeleteMapping("/topics")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse DeleteTopicsById(@RequestParam("id") int id) throws Exception {
        topicsService.DeleteTopicsById(id);
        return CommResponse.success("", Code.B127.getCode());
    }


    /**
     * Add user
     *
     * @param name
     * @param email
     * @param about
     * @param password
     */
    @ApiOperation("Add user")
    @PostMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse AddUser(@RequestParam("name") String name,
                                @RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("about") String about) throws Exception {

        UsersDto usersDto = new UsersDto(name, email, password, about);
        UsersDto usersDtoReturn = usersService.AddUser(usersDto);
        return CommResponse.success(usersDtoReturn, Code.B131.getCode());
    }


    /**
     * Update user by id
     *
     * @param id
     * @param name
     * @param email
     * @param about
     * @param password
     */
    @ApiOperation("Update user by id")
    @PutMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse UpdateUserById(@RequestParam("id") int id,
                                       @RequestParam("name") String name,
                                       @RequestParam("email") String email,
                                       @RequestParam("password") String password,
                                       @RequestParam("about") String about) throws UniqueNameException {
        UsersDto usersDto = new UsersDto(id, name, email, password, about);
        UsersDto usersDtoReturn = usersService.UpdateUserById(usersDto);
        return CommResponse.success(usersDtoReturn, Code.B135.getCode());
    }


    /**
     * Delete user by id
     *
     * @param id
     */
    @ApiOperation("Delete user by id")
    @DeleteMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse DeleteUserById(@RequestParam("id") int id) throws Exception {
        usersService.DeleteUserById(id);
        return CommResponse.success("", Code.B137.getCode());
    }


    /**
     * Get all users
     */
    @ApiOperation("Get all users")
    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse GetAllUsers() {

        List<UsersDto> usersDtoList = usersService.GetAllUsersNoPages();
        return CommResponse.success(usersDtoList, Code.B133.getCode());

    }

    /**
     * Get user by id
     *
     * @param id
     */
    @ApiOperation("Get user by id")
    @GetMapping(value = "/users/{id}" )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse GetUserById(@PathVariable("id") int id) {
        UsersDto usersDto = usersService.GetUsersById(id);
        return CommResponse.success(usersDto, Code.B133.getCode());
    }

    /**
     * Delete article by id
     *
     * @param id
     */
    @ApiOperation("Delete article by id")
    @DeleteMapping("/articles")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CommResponse DeleteArticleById(@RequestParam("id") int id) {
        articlesService.DeleteArticleById(id);
        return CommResponse.success("", Code.B133.getCode());
    }







}
