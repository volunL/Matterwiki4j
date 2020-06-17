package com.brainboom.matterwiki4jboot.controller;


import com.brainboom.matterwiki4jboot.dto.UsersDto;
import com.brainboom.matterwiki4jboot.error.Code;
import com.brainboom.matterwiki4jboot.response.CommResponse;
import com.brainboom.matterwiki4jboot.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RootController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/setup")
    public CommResponse setup(@RequestParam("name") String name, @RequestParam("about") String about,
                              @RequestParam("email") String email, @RequestParam("password") String password) throws Exception {

        UsersDto usersDto = usersService.setup(new UsersDto(1, name, email, password, about));
        return CommResponse.success(usersDto, Code.B131.getCode());
    }





}
