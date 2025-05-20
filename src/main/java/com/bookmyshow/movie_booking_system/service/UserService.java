package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.request.LoginDTO;
import com.bookmyshow.movie_booking_system.dto.request.PostUserDTO;
import com.bookmyshow.movie_booking_system.dto.request.PutUserRequestDTO;
import com.bookmyshow.movie_booking_system.dto.response.AuthResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.PutUserResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.exception.dto.InvalidCredentialsException;
import com.bookmyshow.movie_booking_system.exception.dto.UserAlreadyExistsException;
import com.bookmyshow.movie_booking_system.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
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

    public AuthResponseDTO fetchCurrentUserByEmail(String email) {
        Optional<User> exisitingUserOptional = userRepository.findByEmail(email);
        if (exisitingUserOptional.isEmpty()) {
            log.info("User with email: {} not found", email);
            throw new InvalidCredentialsException("User Not Found");
        }
        User user = exisitingUserOptional.get();
        return new AuthResponseDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
    }


    public PutUserResponseDTO updateUser(PutUserRequestDTO userData) {
        Optional<User> userOptional = userRepository.findByEmail(userData.getEmail());
        if (userOptional.isEmpty()) {
            throw new InvalidCredentialsException("User Not Found");
        }
        User user = userOptional.get();
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setPhoneNumber(userData.getPhoneNumber());
        User updatedUser = userRepository.save(user);
        return new PutUserResponseDTO("User Updated Successfully", updatedUser.getPhoneNumber(), updatedUser.getFirstName(), updatedUser.getLastName());
    }
}