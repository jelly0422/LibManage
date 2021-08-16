package com.library.manage.model.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 响应封装对象
 * @author jelly
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaseResponse <T>{

    //响应信息
    private String message;
    //响应数据
    private T Data;

    /**
     * 成功的响应
     * @param message 信息
     * @param data 数据
     * @param <T> 数据类型
     * @return
     */
    @NonNull
    public static <T> BaseResponse<T> ok(@Nullable String message, @Nullable T data) {
        return new BaseResponse<>( message, data);
    }

    /**
     * 只含有信息的成功响应
     * @param message 信息
     * @param <T> 数据类型
     * @return
     */
    @NonNull
    public static <T> BaseResponse<T> ret(@Nullable String message) {
        return new BaseResponse<>(message, null);
    }

    /**
     * 只含有数据的成功响应. (Default message is OK, status is 200)
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return base response with data
     */
    public static <T> BaseResponse<T> ok(@Nullable T data) {
        return new BaseResponse<>(HttpStatus.OK.getReasonPhrase(), data);
    }
}
