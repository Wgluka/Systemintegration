package com.webservice.controller;

import com.webservice.exception.BadRequestException;
import com.webservice.model.entity.User;
import com.webservice.model.input.ScoreDTO;
import com.webservice.model.input.UserDTO;
import com.webservice.model.output.ScoreInfo;
import com.webservice.model.output.UserInfo;
import com.webservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by yukai on 2016/10/13.
 */
@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public UserInfo authorization(@RequestBody UserDTO userDTO, HttpSession httpSession){
        UserInfo user = userService.authorization(userDTO);
        httpSession.setAttribute("ID", user.getId());
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public UserInfo score(@RequestBody ScoreDTO scoreDTO, HttpSession httpSession){
        Integer id = (Integer) httpSession.getAttribute("ID");
        if (id == null )
            throw new BadRequestException("用户未登陆!", "fail_score");
        return userService.score(id,scoreDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/rangingList", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<ScoreInfo> ranging(HttpSession httpSession){
        logger.info("ranging");
        Integer id = (Integer) httpSession.getAttribute("ID");
        if (id == null )
            throw new BadRequestException("用户未登陆!", "fail_ranging");
        return userService.ranging();
    }

    @ResponseBody
    @RequestMapping(value = "/rangingLists", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<ScoreInfo> rangingWithOtherServer(HttpSession httpSession){
        Integer id = (Integer) httpSession.getAttribute("ID");
//        if (id == null)
//            throw new BadRequestException("用户未登陆!", "fail_rangings");

        return userService.rangingWithOtherServers();
    }


}
