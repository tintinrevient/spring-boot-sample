package com.hncy.demo.service;

import com.hncy.demo.domain.User;
import com.hncy.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Transactional
    public List<User> addRoleToAllUsers(String role) {
        List<User> userList = new ArrayList<>();

        for(User user : userRepository.findAll()) {
            String roleString = user.getRoles();
            Set<String> roleSet = new HashSet<>(Arrays.asList(roleString.split("\\s*,\\s*")));

            roleSet.add(role);
            roleString = String.join(",", roleSet);

            user.setRoles(roleString);
            user = userRepository.save(user);

            userList.add(user);
        }

        return userList;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

}
