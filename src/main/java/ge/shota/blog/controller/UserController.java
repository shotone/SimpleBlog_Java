package ge.shota.blog.controller;

import ge.shota.blog.model.UserModel;
import ge.shota.blog.service.UserService;
import ge.shota.blog.storage.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserModel user) {
        // create user with model which takes from request body
        return userService.saveProfile(user);
    }

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasRole('USER')")
    public Optional<User> getProfile(@PathVariable("id") Long id) {
        // get profile id from path to return optional object of user
        return userService.getProfile(id);
    }



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
