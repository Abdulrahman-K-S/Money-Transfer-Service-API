package com.banquemisr.moneytransactionservice.service;

import com.banquemisr.moneytransactionservice.dto.CreateUserDTO;
import com.banquemisr.moneytransactionservice.dto.UserDTO;
import com.banquemisr.moneytransactionservice.exception.custom.UserAlreadyExistsException;
import com.banquemisr.moneytransactionservice.model.User;

public interface IUser {

    /**
     * Checks if the username or email passed are present in the database.
     *
     * @param username The user's username
     * @param email The user's email
     * @throws UserAlreadyExistsException if the username or email are already present.
     * */
    void checkIfUsernameOrEmailExists(String username, String email) throws UserAlreadyExistsException;

    /**
     * Extracts the info from the createUserDTO into a User object
     *
     * @param createUserDTO The user's register info
     * @return user details @{@link User}
     * */
    User extractUserDetails(CreateUserDTO createUserDTO);

    /**
     * Register the user into the database.
     *
     * @param createUserDTO The user's register info
     * @return registered user @{@link UserDTO}
     * */
    UserDTO register(CreateUserDTO createUserDTO);
}
