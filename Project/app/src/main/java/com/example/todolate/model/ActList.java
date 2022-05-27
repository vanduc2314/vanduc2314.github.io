package com.example.todolate.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ActList implements Serializable {
    private String id;
    private String name;

    public ActList() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
