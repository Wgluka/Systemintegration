package com.webservice.model.input;

/**
 * Created by yukai on 2016/10/13.
 */
public class ScoreDTO {
//    private Integer id;
    private Integer score;

    public ScoreDTO() {
    }

//    public ScoreDTO(Integer id, Integer score) {
//        this.id = id;
//        this.score = score;
//    }

//    public ScoreDTO(Integer id) {
//        this.id = id;
//    }


    public ScoreDTO(Integer score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
}
