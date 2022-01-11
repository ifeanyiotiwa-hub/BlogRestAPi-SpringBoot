package dev.gxsoft.blogrestapi2.serviceImpl;

import dev.gxsoft.blogrestapi2.model.User;
import dev.gxsoft.blogrestapi2.repository.UserRepository;
import dev.gxsoft.blogrestapi2.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loginUser(String email, String password) {
        var loggedUser = userRepository.findUserByEmailAndPassword(email, password);
        if (loggedUser.isPresent()) {
            logger.info("User Found");
            return loggedUser.get();
        } else {
            logger.error("User Not Found");
            throw new RuntimeException("User Not found");
        }
    }

    @Override
    public String registerUser(User user) {
        logger.info("registering User");
        var savedUser = userRepository.save(user);
        logger.info(String.valueOf(savedUser));
        return "User registered";
    }

    @Override
    public String deactivateUser(User user) {
        var dUser = userRepository.findById(user.getUserId());
        if (dUser.isPresent()) {
            var tempUser = dUser.get();
            if (tempUser.isDeactivated()) {
                user.setDeactivated(true);
                userRepository.save(user);
                logger.info("User Saved");
                return "User Saved";
            } else {
                logger.info("User has been Deactivated");
                return "User has been Deactivated";
            }
        } else {
            logger.info("User does not exist");
            return "User does not exist";
        }
    }

    public User findUserById(long id) {
        var foundUser = userRepository.findById(id);

        if (foundUser.isPresent()) {
            logger.info("User Found");
            return foundUser.get();
        } else {
            logger.error("User Not Found");
            throw  new RuntimeException("No such ID");

        }
    }

    @Override
    public User updateUser(User user) {
        long id = user.getUserId();
        var temp = this.findUserById(id);
        if (temp != null) {
            return userRepository.save(user);
        } else {
            logger.info("User not found");
            throw new RuntimeException("User Not found");
        }
    }
}
