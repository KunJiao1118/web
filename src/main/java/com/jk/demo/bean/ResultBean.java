package com.jk.demo.bean;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {
    private HttpStatus code = HttpStatus.OK;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.code = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
