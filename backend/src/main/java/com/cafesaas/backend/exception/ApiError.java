package com.cafesaas.backend.exception;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError<T>{
    private Integer status;
    private ApiErrorExceotion exceotion;

    public static class ApiErrorExceotion{
        private Date createTime;
        private String code;
        private String message;
    }
}
