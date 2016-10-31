package com.webservice.model.output;

/**
 * Created by yukai on 2016/10/13.
 */
public class ScoreInfo implements Comparable {
    private String name;
    private Integer score;

    public ScoreInfo(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public ScoreInfo() {
    }

    @Override
    public String toString() {
        return super.toString();
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

    @Override
    public int compareTo(Object o) {
        ScoreInfo scoreInfo = (ScoreInfo) o;
        if (this.getScore() <= scoreInfo.getScore())
            return 1;
        return -1;
    }
}
