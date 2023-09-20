package com.abi.cucumbertests.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;


@Getter
@Entity
public class Tag {
    @Id
    private int id;
    private String name;

    public Tag(){
    }

    public Tag(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }




}
