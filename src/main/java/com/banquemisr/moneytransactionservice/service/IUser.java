package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.exception.custom.UserNotFoundException;
import com.banquemisr.moneytransactionservice.model.User;

public interface IUser {

    /**
     * Retrieves the user with id passed if exists
     *
     * @param userId The user's ID
     * @return The user object @{@link User}
     * @throws UserNotFoundException If user doesn't exist with passed id
     * */
    User getUserIfExistsById(Long userId) throws UserNotFoundException;

    /**
     * Retrieves the user with email passed if exists
     *
     * @param email The user's email
     * @return The user object @{@link User}
     * @throws UserNotFoundException If user doesn't exist with passed email
     * */
    User getUserIfExistsByEmail(String email) throws UserNotFoundException;
}
