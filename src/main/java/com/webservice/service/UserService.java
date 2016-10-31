package com.webservice.service;

import com.webservice.model.entity.User;
import com.webservice.model.input.ScoreDTO;
import com.webservice.model.input.UserDTO;
import com.webservice.model.output.ScoreInfo;
import com.webservice.model.output.UserInfo;

import java.util.List;

/**
 * Created by yukai on 2016/10/13.
 */
public interface UserService {
    void addUser(UserDTO userDTO);
    UserInfo authorization(UserDTO userDTO);
    UserInfo score(Integer id, ScoreDTO scoreDTO);
    List<ScoreInfo> ranging();
    List<ScoreInfo> rangingWithOtherServers();
}
