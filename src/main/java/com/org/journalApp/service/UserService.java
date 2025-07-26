package com.org.journalApp.service;

import com.org.journalApp.entity.User;
import com.org.journalApp.repository.JournalEntryRepository;
import com.org.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {

    //if we use @Slf4j annotation on this class which is a lombok annotation then it
    //injects the logger instance to the class so we can skip manually creating the instance as given below
    //but the instance created using the annotation is log not logger, so use log instead of logger provided by lombok
    //private static final Logger logger= LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean saveNewUser(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            log.info("error occurred for {} :",user.getUsername(),e); // here we can pass multiple place-holders if we want to pass the arguments
            log.warn("everything not good got exception, please check error",e);
            log.error("everything not good got exception, please check error",e); //by default till this three it will print the log
            log.trace("everything not good got exception, please check error",e);
            log.debug("everything not good got exception, please check error",e);
            return false;
        }
    }

    public boolean saveAdmin(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> getById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}
