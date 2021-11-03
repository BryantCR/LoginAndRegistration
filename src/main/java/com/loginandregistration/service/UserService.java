package com.loginandregistration.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.loginandregistration.models.User;
import com.loginandregistration.repositories.UserRepository;

@Service
public class UserService {
	
private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    public Integer  registerUser( String email, String password ) {
		String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		return userRepository.insertNewUser(email, encryptedPassword);
	}
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getUserByEmail( String email ){
		return userRepository.selectUserByEmail(email);
	}
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate usercopy
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public boolean validateUser( User currentUser, String password ) {
		return BCrypt.checkpw( password, currentUser.getPassword() );
	}
    

}
