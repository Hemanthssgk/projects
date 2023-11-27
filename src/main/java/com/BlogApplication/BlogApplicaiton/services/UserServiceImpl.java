package com.BlogApplication.BlogApplicaiton.services;

import com.BlogApplication.BlogApplicaiton.exceptions.NoResourceFoundException;
import com.BlogApplication.BlogApplicaiton.repositories.RoleRepo;
import com.BlogApplication.BlogApplicaiton.repositories.UserRepository;
import com.BlogApplication.BlogApplicaiton.repositories.entity.Role;
import com.BlogApplication.BlogApplicaiton.repositories.entity.User;
import com.BlogApplication.BlogApplicaiton.services.interfaces.UserService;
import com.BlogApplication.BlogApplicaiton.services.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserDTO createUser(User user) throws Exception {
        if (user == null)
        {
            throw new Exception("Empty User, provide the correct userDetails");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // by default keeping the user role as user not admin
        Role role = roleRepo.findById(502).get();
        user.getRoles().add(role);

        userRepository.save(user);

        return userTouserDto(user);
    }

    @Override
    public UserDTO updateUser(User user, Integer id) throws Exception {
        if (user.getEmail()==null||!userRepository.existsById(id))
        {
            throw new NoResourceFoundException("User","id",id);
        }
//        userRepository.save(new User(user.getUserId(), user.getName(),user.getEmail(),user.getPassword(),user.getAbout()));
        //setting id cause sending id as null jpa think it is new entity and creates a new one. so either uncomment and comment below line or use as it is.
        user.setUserId(id);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // by default keeping the user role as user not admin
        Role role = roleRepo.findById(502).get();
        user.getRoles().add(role);
        userRepository.save(user);
        return userTouserDto(user);
    }

    @Override
    public void deleteUser(Integer id) {
        if (!userRepository.existsById(id))
        {
            throw new NoResourceFoundException("User","id",id);
        }

        userRepository.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Integer id) {
        User user = null;
        if (id!=null) {
            user =  userRepository.findById(id).orElseThrow(() -> new NoResourceFoundException("User","id",id));
        }
        return userTouserDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> userDTOList = new ArrayList<>();
//        for(User user : userRepository.findAll())
//        {
//            userDTOList.add(userTouserDto(user));
//        }
        return userRepository.findAll().stream().map(this::userTouserDto).collect(Collectors.toList());
    }

    public UserDTO userTouserDto(User user)
    {
        if (user!=null)
//        return new UserDTO(user.getUserId(),user.getName(), user.getEmail(), user.getAbout());
        return modelMapper.map(user, UserDTO.class);
        else
            return null;
    }
    public User userDTOTouser(UserDTO userDTO)
    {
        if (userDTO!=null)
//        return new UserDTO(user.getUserId(),user.getName(), user.getEmail(), user.getAbout());
        return modelMapper.map(userDTO, User.class);
        else
            return null;
    }
}
