package com.example.my.akka.java.demo.persistence;

import java.io.Serializable;

/**
 * @Description:
 * @Author: zhoufengen
 * @Create At: 2019-12-18 14:20
 **/
public class Evt implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String data;
    private final String uuid;

    public Evt(String data, String uuid) {
        this.data = data;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getData() {
        return data;
    }
}