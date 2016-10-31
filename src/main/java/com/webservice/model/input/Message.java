package com.webservice.model.input;

import java.util.List;

/**
 * Created by yukai on 2016/10/28.
 */
public class Message {
    private boolean succeed;
    private List<OtherDTO> data;
    private String error;

    @Override
    public String toString() {
        return "Message{" +
                "succeed=" + succeed +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }

    public Message(boolean succeed, List<OtherDTO> data, String error) {
        this.succeed = succeed;
        this.data = data;
        this.error = error;
    }

    public Message() {

    }

    public boolean isSucceed() {

        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public List<OtherDTO> getData() {
        return data;
    }

    public void setData(List<OtherDTO> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
