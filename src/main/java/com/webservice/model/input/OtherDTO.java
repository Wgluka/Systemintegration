package com.webservice.model.input;

/**
 * Created by yukai on 2016/10/28.
 */
public class OtherDTO {
    private String username;
    private Integer score;

    @Override
    public String toString() {
        return "OtherDTO{" +
                "username='" + username + '\'' +
                ", score=" + score +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public OtherDTO(String username, Integer score) {

        this.username = username;
        this.score = score;
    }

    public OtherDTO() {

    }
}
