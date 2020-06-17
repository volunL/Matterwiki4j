package com.brainboom.matterwiki4jboot.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RespUtil {





        public static void ReturnJsonResponse(HttpServletResponse response, Object o) throws IOException {
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(), o);
        }




}
