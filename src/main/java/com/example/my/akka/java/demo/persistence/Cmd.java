package com.example.my.akka.java.demo.persistence;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhoufengen
 * @Create At: 2019-12-18 14:19
 **/
public class Cmd implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String data;

    public Cmd(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
