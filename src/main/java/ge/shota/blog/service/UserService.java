package ge.shota.blog.service;

import ge.shota.blog.model.UserModel;
import ge.shota.blog.repository.UserRepository;
import ge.shota.blog.service.impl.UserServiceImpl;
import ge.shota.blog.storage.User;
import ge.shota.blog.util.GenerateUtil;
import ge.shota.blog.util.ValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements UserServiceImpl {

    private UserRepository userRepository;
    private GenerateUtil generateUtil;


    @Override
    public User saveProfile(UserModel model) {

        try {
            if (ValidateUtil.validateUserName(model.getUserName()) && !userRepository.existsByUserNameAndDeleteTimeIsNull(model.getUserName())) {
                model.toBuilder()
                        .password(generateUtil.passwordEncode(model.getPassword()))
                        .build();
                return userRepository.save(new User(model));
            }

        }catch (Exception e) {
            log.error("Unknown Error during creating profile: {}", e.getMessage());
            e.fillInStackTrace();
        }
        return null;


    }


    @Override
    public Optional<User> getProfile(Long id) {
        return userRepository.findById(id);
    }




    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
