package com.edirectinsure.taskmanager.service;

import com.edirectinsure.taskmanager.exception.UserAlreadyExistsException;
import com.edirectinsure.taskmanager.model.User;
import com.edirectinsure.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.edirectinsure.taskmanager.security.WebSecurityConfig.ENCRYPT_STRENGTH;

@Service
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(ENCRYPT_STRENGTH);
    }

    public User create(User user){
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExistsException(user.getUsername());
        }

        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        return userRepository.save(user);
    }
}