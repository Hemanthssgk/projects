package com.BlogApplication.BlogApplicaiton.rest.services.interfaces;

import com.BlogApplication.BlogApplicaiton.rest.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.rest.services.model.UserDTO;

import java.util.List;

public interface UserService {

    String loginuser() throws Exception;
    UserDTO createUser(User user) throws Exception;
    UserDTO updateUser(User user, Integer id) throws Exception;
    void deleteUser(Integer id);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();


}
