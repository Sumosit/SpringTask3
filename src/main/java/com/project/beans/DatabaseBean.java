package com.project.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class DatabaseBean {

    private String data;

    public DatabaseBean(){
        this.data = "Hello My App";
    }

    public DatabaseBean(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
