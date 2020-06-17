package com.brainboom.matterwiki4jboot.services.impl;

import com.brainboom.matterwiki4jboot.dto.TopicsDto;
import com.brainboom.matterwiki4jboot.dto.UsersDto;
import com.brainboom.matterwiki4jboot.entity.Users;
import com.brainboom.matterwiki4jboot.repository.UsersRepository;
import com.brainboom.matterwiki4jboot.security.jwt.TokenManager;
import com.brainboom.matterwiki4jboot.services.TopicsService;
import com.brainboom.matterwiki4jboot.services.UsersService;
import com.brainboom.matterwiki4jboot.services.exception.UniqueNameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {


    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private TokenManager tokenManager;


    /**
     * add user
     *
     * @param usersDto
     * @return usersDto
     */
    @Override
    public UsersDto AddUser(UsersDto usersDto) throws UniqueNameException {

        String uname = usersDto.getName();
        String email = usersDto.getEmail();
        if (usersRepository.existsByName(uname)) {
            log.error("name " + uname + " has been used");
            throw new UniqueNameException("name " + uname + " has been used");
        }

        if (usersRepository.existsByEmail(email)) {
            log.error("email " + email + " has been used");
            throw new UniqueNameException("email " + email + " has been used");
        }


        Users users = usersDto.convertToUsers();


        //encrypt password
        String password = users.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        users.setPassword(bCryptPasswordEncoder.encode(password));
        //save
        usersRepository.saveAndFlush(users);
        Users usersReturn = usersRepository.findUsersByNameAndEmailAndAbout(usersDto.getName(), usersDto.getEmail(), usersDto.getAbout());
        UsersDto usersDtoReturn = UsersDto.convertFromUsers(usersReturn);
        return usersDtoReturn;
    }


    /**
     * delete user
     *
     * @param id
     */
    @Override
    public void DeleteUserById(int id) throws Exception {

        if (id == 1) {
            log.error("Can not delete admin");
            throw new Exception("Can not delete admin");
        }

        usersRepository.deleteById(id);
    }


    /**
     * update user
     *
     * @param usersDto
     * @return usersDto
     */
    @Override
    public UsersDto UpdateUserById(UsersDto usersDto) throws UniqueNameException {

        String uname = usersDto.getName();
        String email = usersDto.getEmail();
        if (usersRepository.existsByEmailAndIdNotLike(uname, usersDto.getId())) {
            log.error("name " + uname + " has been used");
            throw new UniqueNameException("name " + uname + " has been used");
        }

        if (usersRepository.existsByEmailAndIdNotLike(email, usersDto.getId())) {
            log.error("email " + email + " has been used");
            throw new UniqueNameException("email " + email + " has been used");
        }


        Users users = usersDto.convertToUsers();

        //encrypt password
        String password = users.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        users.setPassword(bCryptPasswordEncoder.encode(password));
        //save
        usersRepository.saveAndFlush(users);

        Users usersReturn = usersRepository.findUsersById(usersDto.getId());

        UsersDto usersDtoReturn = UsersDto.convertFromUsers(usersReturn);

        return usersDtoReturn;
    }


    /**
     * get all users without pages
     *
     * @return List<UsersDto>
     */
    @Override
    public List<UsersDto> GetAllUsersNoPages() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDto> usersDtoList = UsersDto.convertFromUsersList(usersList);
        return usersDtoList;
    }


    /**
     * get  user by id
     *
     * @param id
     * @return UsersDto
     */
    @Override
    public UsersDto GetUsersById(int id) {
        Users users = usersRepository.findUsersById(id);
        UsersDto usersDto = UsersDto.convertFromUsers(users);
        return usersDto;

    }

    /**
     * setup for admin
     *
     * @param usersDto
     * @return UsersDto
     */
    public UsersDto setup(UsersDto usersDto) throws UniqueNameException {
        Users users = usersRepository.findUsersById(1);

        if (users != null) {
            log.error("admin user  \"" + users.getName() + "\" has been set!");
            throw new UniqueNameException("admin user  \"" + users.getName() + "\" has been set!");
        } else {
            UsersDto usersDto1 = AddUser(usersDto);
            topicsService.InsertTopics(new TopicsDto("general", "knowledge for everyone"));
            return usersDto1;
        }


    }


    /**
     * authenticate
     *
     * @param usersDto
     * @return UsersDto
     */
    public HashMap<String, Object> authenticate(UsersDto usersDto) throws Exception {
        String token = "";
        Users usersIndb = usersRepository.findUsersByEmail(usersDto.getEmail());

        if (usersIndb == null) {
            log.error("User not found");
            throw new Exception("User not found");
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        boolean isMatch = bCryptPasswordEncoder.matches(usersDto.getPassword(), usersIndb.getPassword());

        if (isMatch) {
            token = tokenManager.createToken(usersDto.getEmail());
        } else {
            log.error("Email or Password is wrong");
            throw new Exception("Email or Password is wrong");
        }


        HashMap<String, String> hashuser = new HashMap<>();
        HashMap<String, Object> returnhash = new HashMap<>();
        hashuser.put("email", usersIndb.getEmail());
        hashuser.put("id", usersIndb.getId() + "");
        returnhash.put("token", token);
        returnhash.put("user", hashuser);


        return returnhash;


    }


}
