package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.request.LoginDTO;
import com.bookmyshow.movie_booking_system.dto.request.PostUserDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.exception.dto.InvalidCredentialsException;
import com.bookmyshow.movie_booking_system.exception.dto.UserAlreadyExistsException;
import com.bookmyshow.movie_booking_system.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public PostUserResponseDTO addUser(PostUserDTO userDTO) {
        User user = new User();
        Optional<User> exisitingUserOptional = userRepository.findByEmail(userDTO.getEmail());
        if (exisitingUserOptional.isPresent()) {
            throw new UserAlreadyExistsException("Email already exists. Please login");
        }
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());

        User savedUser = userRepository.save(user);
        return new PostUserResponseDTO(savedUser.getId(), savedUser.getEmail(), "Signup Successful");
    }


    public User findOrCreateUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setPhoneNumber(phoneNumber);
                    return userRepository.save(newUser);
                });
    }

    public User authenticateUser(@Valid LoginDTO loginDTO) {
        Optional<User> exisitingUserOptional = userRepository.findByEmail(loginDTO.getEmail());
        if (exisitingUserOptional.isEmpty()) {
            throw new InvalidCredentialsException("Invalid Credentials.");
        }
        User existingUser = exisitingUserOptional.get();
        if (!loginDTO.getPassword().equals(existingUser.getPassword())) {
            throw new InvalidCredentialsException("Invalid Credentials.");
        }
        return existingUser;
    }

    public User findUserByEmail(String email) {
        Optional<User> exisitingUserOptional = userRepository.findByEmail(email);
        return exisitingUserOptional.orElse(null);
    }

    public String extractJwtFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("authToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

//    public UserDTO getUserDetails(Long userId) {
//        Optional<User> userOptional = userRepository.findById(userId);
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException("User Doesn't Exist");
//        }
//        User user = userOptional.get();
//        return new UserDTO(user.getUsername(), user.getEmailId(), user.getPhoneNumber());
//    }
//
//    public UserDTO updateUser(PutUserDTO putUserDTO) {
//        Optional<User> userOptional = userRepository.findById(putUserDTO.getUserId());
//        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException("User Doesn't Exist");
//        }
//        User user = userOptional.get();
//        user.setUsername(putUserDTO.getUsername());
//        user.setEmailId(putUserDTO.getEmailId());
//        User savedUser = userRepository.save(user);
//        return new UserDTO(savedUser.getUsername(), savedUser.getEmailId(), savedUser.getPhoneNumber());
//    }
}