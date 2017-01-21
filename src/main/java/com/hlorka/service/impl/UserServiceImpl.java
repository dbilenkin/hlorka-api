package com.hlorka.service.impl;

import com.hlorka.domain.User;
import com.hlorka.service.IntSequence;
import com.hlorka.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dbilenkin on 1/21/17.
 */
@Service
public class UserServiceImpl implements UserService {

    private final IntSequence userIdSeq = new IntSequence();

    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User getUser(String login) {
        User user = users.get( login);
        if (user == null) {
            user = new User(userIdSeq.next(), login);
        }

        return user;
    }
}
