package com.ssafy.smaheal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

import com.ssafy.smaheal.exception.ResourceNotFoundException;
import com.ssafy.smaheal.help.UserIdentityAvailability;
import com.ssafy.smaheal.help.UserProfile;
import com.ssafy.smaheal.model.MemberUser;
import com.ssafy.smaheal.model.UserRoles;
import com.ssafy.smaheal.repository.UserRepository;
import com.ssafy.smaheal.repository.UserRolesRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserRolesRepository userRolesRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByNickName(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/get/{num}")
    public MemberUser getUserProfileByNum(@PathVariable(value = "num") Long num) {
        MemberUser user = userRepository.findByNum(num)
                .orElseThrow(() -> new ResourceNotFoundException("User", "num", num));

        return user;
    }
    
    @GetMapping("/users/{userId}")
    public UserProfile getUserProfile(@PathVariable(value = "userId") String userId) {
        MemberUser user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        UserProfile userProfile = new UserProfile(user.getNum(), user.getUserId(), user.getName(), user.getNickName(), user.getBirth());
        return userProfile;
    }

    @PutMapping("/{num}")
    public ResponseEntity<String> modifyUserProfile(@RequestBody MemberUser memberUser, @PathVariable(value = "num") Long num) {
    	MemberUser user = null;
    	
    	String SUCCESS = "success";
        try {
        	user = userRepository.findByNum(num)
        			.orElseThrow(() -> new ResourceNotFoundException("User", "num", num));
        	user.setName(memberUser.getName());
        	user.setNickName(memberUser.getNickName());
        	user.setBirth(memberUser.getBirth());
        } catch (Exception e) {
        	return null;
        }
        MemberUser updateUser = userRepository.save(user); 
        
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{num}")
    public ResponseEntity<?> deleteUser(@PathVariable("num") Long num) {
    	UserRoles userRoles = userRolesRepository.findByUserNum(num);
    	userRolesRepository.delete(userRoles);
      try {
    	  MemberUser user = userRepository.findByNum(num)
        			.orElseThrow(() -> new ResourceNotFoundException("User", "num", num));
    	  userRepository.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
    
    @GetMapping("checkId/{id}")
    public Object checkId(@PathVariable String id) throws SQLException, IOException {
      try {
        return new ResponseEntity<>(userRepository.findByUserId(id), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

    @GetMapping("checkNick/{nickName}")
    public Object checkNick(@PathVariable String nickName) throws SQLException, IOException {
      try {
        return new ResponseEntity<>(userRepository.findByNickName(nickName), HttpStatus.OK);
      } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
