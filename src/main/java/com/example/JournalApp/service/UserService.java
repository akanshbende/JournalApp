package com.example.JournalApp.service;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepository;
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

        @Autowired
        private UserRepository userRepository;

        private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        private static final Logger logger= LoggerFactory.getLogger(UserService.class);

        public void saveEntry(User user){
            userRepository.save(user);
        }

        public boolean saveNewUser(User user){
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList("USER"));
                userRepository.save(user);
                return true;
            } catch (RuntimeException e) {
//                logger.error("Error Occurred {}: ",user.getUserName(),e);
//                slf4j annotation:
                log.error("Error Occurred {}: ",user.getUserName());
                log.error("Error Occurred {}: ",user.getUserName());
                log.error("Error Occurred {}: ",user.getUserName());

//                logger.warn("Error Occurred : ",e);
//                logger.info("Error Occurred : ",e);
//                logger.debug("Error Occurred : ",e);
//                logger.trace("Error Occurred : ",e);
                return false;
            }
        }

        public void saveAdmin(User user){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER","ADMIN"));
            userRepository.save(user);
        }

        public List<User> getAll(){
            return userRepository.findAll();
        }

        public Optional<User> findById(ObjectId id){
            return userRepository.findById(id);
        }

        public void deleteById(ObjectId id){
            userRepository.deleteById(id);
        }

        public User findByUserName(String username){
            return userRepository.findByUserName(username);
        }

        public void deleteAllUsers(){
            userRepository.deleteAll();
        }

        public void deleteByusername(String userName){
            userRepository.deleteByUserName(userName);
        }


}
