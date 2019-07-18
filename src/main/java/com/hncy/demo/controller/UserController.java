package com.hncy.demo.controller;

import com.hncy.demo.repository.UserRepository;
import com.hncy.demo.domain.User;
import com.hncy.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/user")
@Api(description="Operations about users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find a user by its id", response = User.class)
    public User find(@ApiParam(value = "user id", required = true) @PathVariable("id") Long id) {
        return userRepository.findById(id).get();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a user with its id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@ApiParam(value = "user data", required = true) @RequestBody User user) {
        userRepository.save(user);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update a user by its id", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@ApiParam(value = "user id", required = true) @PathVariable("id") Long id, @ApiParam(value = "user data", required = true) @RequestBody User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete a user by its id")
    public void delete(@ApiParam(value = "user id", required = true) @PathVariable("id") Long id) {
        userRepository.deleteById(id);
    }

    @GetMapping(value = "/addRoleToAllUsers")
    @ApiOperation(value = "Add a new role to all the users")
    public void addRoleToAllUsers(@ApiParam(value = "a single role string", required = true) @RequestParam("role") String role) {
        userService.addRoleToAllUsers(role);
    }

    @GetMapping(value = "/findSortedAll")
    @ApiOperation(value = "Get all the users sorted by one of its attributes alphabetically", response = List.class)
    public List<User> findSortedAll(@ApiParam(value = "user attribute", required = true, allowableValues = "id,username,fullname,roles") @RequestParam("sort") String sort) {
        return userRepository.findAll(new Sort(sort));
    }

    @GetMapping(value = "/findPagedAll")
    @ApiOperation(value = "Get the users by pagination", response = Page.class)
    public Page<User> findPagedAll(@ApiParam(value = "start page", required = true) @RequestParam("page") int page, @ApiParam(value = "page size", required = true) @RequestParam("size") int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

}
