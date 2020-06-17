package com.brainboom.matterwiki4jboot.security.filter;

import com.brainboom.matterwiki4jboot.error.Code;
import com.brainboom.matterwiki4jboot.response.CommResponse;
import com.brainboom.matterwiki4jboot.security.jwt.TokenManager;
import com.brainboom.matterwiki4jboot.security.util.RespUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


/**
 * authenticate token
 *
 * @author volun
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserDetailsService userDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager
                                    , TokenManager tokenManager, UserDetailsService userDetailsService
    ) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.userDetailsService = userDetailsService;
    }


    /**
     * @param chain
     * @param request
     * @param response
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //super.doFilterInternal(request, response, chain);


        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            chain.doFilter(request, response);
            return;
        }


        try {
            String token = request.getHeader("x-access-token");
            StringBuffer url = request.getRequestURL();


            log.info("ip[{}],url[{}  {}],token:[{}]", request.getRemoteAddr(),request.getMethod(),url, token);



            if (StringUtils.isEmpty(token)) {
                chain.doFilter(request, response);
                return;
            }


            //验证token 获取权限
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //filter链继续
            chain.doFilter(request, response);


        } catch (Exception e) {
            e.printStackTrace();
            CommResponse rm = CommResponse.error(Code.B101.getCode(), "Failed to authenticate token[" + e.getMessage() + "]");
            log.error("Failed to authenticate token[" + e.getMessage() + "],exception:{}",e);
            RespUtil.ReturnJsonResponse(response, rm);
        }

    }


    /**
     * 解析token
     * parse the token
     *
     * @param token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws Exception {


        // parse the token to get username  解析token
        String userName = tokenManager.verify(token);

        log.info("解析token{}的用户名{}", token, userName);

        if (!StringUtils.isEmpty(userName)) {

            Collection<? extends GrantedAuthority> aa = userDetailsService.loadUserByUsername(userName).getAuthorities();

            return new UsernamePasswordAuthenticationToken(
                    userName,
                    token,
                    aa
            );
        } else {
            log.error("token{} 无效", token);
            throw new BadCredentialsException("token 无效");
        }


    }


}
