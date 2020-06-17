package com.brainboom.matterwiki4jboot.security.jwt;

public interface TokenManager {

    String createToken(String username);

    String verify(String token) throws Exception;


}
