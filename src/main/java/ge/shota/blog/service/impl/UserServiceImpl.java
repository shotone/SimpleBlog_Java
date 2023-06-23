package ge.shota.blog.service.impl;

import ge.shota.blog.model.UserModel;
import ge.shota.blog.storage.User;

import java.util.Optional;

public interface UserServiceImpl {

    Optional<User> getProfile(Long id);

    User saveProfile(UserModel model);

}
