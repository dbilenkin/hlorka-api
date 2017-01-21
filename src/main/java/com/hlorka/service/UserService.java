package com.hlorka.service;

import com.hlorka.domain.User;

/**
 * Created by dbilenkin on 1/21/17.
 */
public interface UserService {

    User getUser(String login);
}
