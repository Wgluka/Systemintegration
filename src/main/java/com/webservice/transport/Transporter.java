package com.webservice.transport;

import com.webservice.model.output.ScoreInfo;

import java.util.List;

/**
 * Created by yukai on 2016/10/28.
 */
public interface Transporter {
    List<ScoreInfo> getData();
}
