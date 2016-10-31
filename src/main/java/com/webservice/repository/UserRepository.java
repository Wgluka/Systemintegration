package com.webservice.repository;

import com.webservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yukai on 2016/10/13.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
