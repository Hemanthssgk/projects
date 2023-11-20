package com.BlogApplication.BlogApplicaiton.services.interfaces;

import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user) throws Exception;
    UserDTO updateUser(User user, Integer id) throws Exception;
    void deleteUser(Integer id);
    UserDTO getUserById(Integer id);
    List<UserDTO> getAllUsers();


}
