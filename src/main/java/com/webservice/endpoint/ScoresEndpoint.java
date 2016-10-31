package com.webservice.endpoint;

import com.webservice.model.entity.User;
import com.webservice.model.output.UserInfo;
import com.webservice.repository.UserRepository;
import com.webservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import scores.wsdl.ScoreResponse;
import scores.wsdl.ScoresResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yukai on 2016/10/27.
 */
@Endpoint
public class ScoresEndpoint{

    @Autowired
    private UserRepository userRepository;

    @PayloadRoot(namespace = "http://www.wgluka.com.cn/2048/", localPart = "ScoresRequest")
    public @ResponsePayload ScoresResponse getScores(){
        Sort sort = new Sort(Sort.Direction.DESC, "score");
        List<User> userInfos = userRepository.findAll(sort);
        if (userInfos == null || userInfos.size() == 0)
            return new ScoresResponse();

        ScoresResponse scoresResponse = transToScores(userInfos);

        return scoresResponse;
    }

    private ScoresResponse transToScores(List<User> users){
        List<ScoreResponse> scoreResponses = new ArrayList<ScoreResponse>();
        for (User user : users){
            scoreResponses.add(transToScore(user));
        }
        ScoresResponse scoresResponse = new ScoresResponse();
        scoresResponse.setSocreResponse(scoreResponses);
        return scoresResponse;
    }

    private ScoreResponse transToScore(User user){
        ScoreResponse response = new ScoreResponse();
        response.setName(user.getName());
        response.setScore(new BigInteger(user.getScore().toString()));
        return response;
    }
}
