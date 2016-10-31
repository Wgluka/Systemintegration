package com.webservice.service.impl;

import com.webservice.exception.BadRequestException;
import com.webservice.model.entity.User;
import com.webservice.model.input.ScoreDTO;
import com.webservice.model.input.UserDTO;
import com.webservice.model.output.ScoreInfo;
import com.webservice.model.output.UserInfo;
import com.webservice.repository.UserRepository;
import com.webservice.service.UserService;
import com.webservice.transport.Transporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yukai on 2016/10/13.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transporter transporter;

    @Override
    public void addUser(UserDTO userDTO) {
        User user = userRepository.findByName(userDTO.getName());
        if( user != null )
            throw new BadRequestException("用户已存在！", "fail_register");

        user = convertToUser(userDTO);
        userRepository.save(user);
    }

    @Override
    public UserInfo authorization(UserDTO userDTO) {
        User user = userRepository.findByName(userDTO.getName());
        if( user == null )
            throw new BadRequestException("用户不存在！", "fail_authorization");
        if(checkPassword(user.getPassword(), userDTO.getPassword()))
            return transToUserInfo(user);
        throw new BadRequestException("用户密码错误!", "fail_authorization");
    }

    @Override
    public UserInfo score(Integer id, ScoreDTO scoreDTO) {
        User user = userRepository.findOne(id);
        if ( user == null )
            throw new BadRequestException("用户不存在！", "fail_score");

        if( user.getScore() > scoreDTO.getScore())
            return transToUserInfo(user);

        user.setScore(scoreDTO.getScore());
        userRepository.save(user);

        return transToUserInfo(user);
    }

    @Override
    public List<ScoreInfo> ranging() {
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        List<User> users = userRepository.findAll(sort);
        if (users == null || users.size() == 0)
            throw new BadRequestException("排名为空", "fail_ranging");
        return transToScoreInfos(users);
    }

    @Override
    public List<ScoreInfo> rangingWithOtherServers() {
        if (transporter == null){
            logger.info(" is  null    ");
            return null;
        }

        List<ScoreInfo> scoreInfos = transporter.getData();
        List<ScoreInfo> scoreInfoList = ranging();

        scoreInfos.addAll(scoreInfoList);
        if (scoreInfos.size() == 0)
            return null;

        Collections.sort(scoreInfos);
        return scoreInfos;
    }

    private List<ScoreInfo> transToScoreInfos(List<User> users) {
        List<ScoreInfo> scoreInfos = new ArrayList<ScoreInfo>();
        for( User user: users){
            ScoreInfo scoreInfo = new ScoreInfo(user.getName(), user.getScore());
            scoreInfos.add(scoreInfo);
            System.out.println(scoreInfo.toString());
        }
        return scoreInfos;
    }

    private UserInfo transToUserInfo(User user) {
        return new UserInfo(user.getId(), user.getName(), user.getScore());
    }

    private Boolean checkPassword(String right, String toCheck){
        return right.equals(toCheck);
    }

    private User convertToUser(UserDTO userDTO){
        return new User(userDTO.getName(), userDTO.getPassword(), 0);
    }
}
