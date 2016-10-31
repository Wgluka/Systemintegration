package com.webservice.model.entity;

import javax.persistence.*;

/**
 * Created by yukai on 2016/10/13.
 */
@Entity
@Table(name = "user")
public class User implements Comparable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "score")
    private Integer score;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, Integer score) {
        this.name = name;
        this.password = password;
        this.score = score;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != this.getClass())
            try {
                throw new Exception("error Type");
            } catch (Exception e) {
                e.printStackTrace();
            }

        User user = (User) o;
        if( this.getScore() > user.getScore() )
            return 1;
        else if ( this.getScore() == user.getScore() )
            return 0;
        return -1;
    }
}
