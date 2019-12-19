package com.example.my.akka.java.demo.persistence;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Description:
 * @Author: zhoufengen
 * @Create At: 2019-12-18 14:20
 **/
public class  ExampleState implements Serializable {
    private static final long serialVersionUID = 1L;
    private final ArrayList<String> events;

    public ExampleState() {
        this(new ArrayList<String>());
    }

    public ExampleState(ArrayList<String> events) {
        this.events = events;
    }

    public ExampleState copy() {
        return new ExampleState(new ArrayList<String>(events));
    }

    public void update(Evt evt) {
        events.add(evt.getData());
    }

    public int size() {
        return events.size();
    }

    @Override
    public String toString() {
        return events.toString();
    }
}
