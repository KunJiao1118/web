package com.jk.demo.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultBean<T> implements Serializable {

    //private static final long serialVersionUID = 1L;

    //public static final int NO_LOGIN = -1;

    //public static final int SUCCESS = 0;

    //public static final int FAIL = 1;

    //public static final int NO_PERMISSION = 2;

   // private String msg = "success";

    private int code = 200;

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
        this.code = 500;
    }
}
