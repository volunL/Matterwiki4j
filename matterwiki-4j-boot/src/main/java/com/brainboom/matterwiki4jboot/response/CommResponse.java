package com.brainboom.matterwiki4jboot.response;


import com.brainboom.matterwiki4jboot.error.Error;
import lombok.Data;

import java.io.Serializable;


/**
 * common response class
 * 通用返回类
 * All responses follow a uniform structure across all endpoints.
 *
 * ```
 * {
 *   "error": {
 *     "error": "boolean",
 *     "message": "string"
 *   },
 *   "code": "string",
 *   "data": {
 *     // Contains the response data. Example: array or all topics for a GET /api/topics request
 *   }
 * }
 *
 * @author volun
 * @version 1
 *
 */
@Data
public class CommResponse<T> implements Serializable {


    private String code;
    private T data;
    private Error error;


    public CommResponse(String code, T data, Error error) {
        this.code = code;
        this.data = data;
        this.error = error;
    }

    /**
     * 成功返回
     * success response
     *
     * @param code
     * @param data
     * @return CommResponse
     */
    public static <T> CommResponse success(T data, String code) {

        return new CommResponse(code, data, new Error("", false));
    }


    /**
     * 错误返回
     * error response
     *
     * @param code
     * @param errorMessage
     * @return CommResponse
     */
    public static <T> CommResponse error(String code, String errorMessage) {
        return new CommResponse(code, null, new Error(errorMessage, true));
    }


}
