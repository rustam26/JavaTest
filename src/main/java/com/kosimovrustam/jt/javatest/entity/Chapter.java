package com.kosimovrustam.jt.javatest.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "chapters")
public class Chapter {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "name")
    private String name;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "chapter")
    List<Question> questions;

    public Chapter() {
    }

    public Chapter(String name, List<Question> questions) {
        this.name = name;
    }

    public Chapter(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
