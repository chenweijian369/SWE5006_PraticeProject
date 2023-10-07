package com.nus.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Done by CHEN WEIJIAN
 * Return the standard result to frontend
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

    /**
     * code: 1 is successful, 0 is unsuccessful
     * msg: the error message
     * data: vo data
     */
    private Integer code;
    private String msg;
    private T data;

    /**
     * return the successful notification
     * not need to return data
     * @return
     * @param <T>
     */
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        return result;
    }

    /**
     * return the successful notification
     * need to return data
     * @param object
     * @return
     * @param <T>
     */
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.data = object;
        result.code = 1;
        return result;
    }

    /**
     * return error message
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
