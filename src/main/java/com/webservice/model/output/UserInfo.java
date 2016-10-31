package com.webservice.model.output;

/**
 * Created by yukai on 2016/10/13.
 */
public class UserInfo {
    private Integer id;
    private String name;
    private Integer score;

    public UserInfo(Integer id, String name, Integer score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public UserInfo() {
    }
//    public UserInfo(String name, Integer score) {
//        this.name = name;
//        this.score = score;
//    }


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
}
