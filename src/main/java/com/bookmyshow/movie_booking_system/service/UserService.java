package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.request.PutUserDTO;
import com.bookmyshow.movie_booking_system.dto.request.UserDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.exception.dto.UserNotFoundException;
import com.bookmyshow.movie_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public PostUserResponseDTO addUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmailId(userDTO.getEmailId());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        User savedUser = userRepository.save(user);
        return new PostUserResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getEmailId(), savedUser.getPhoneNumber());

    }


    public User findOrCreateUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setPhoneNumber(phoneNumber);
                    return userRepository.save(newUser);
                });
    }

    public UserDTO getUserDetails(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User Doesn't Exist");
        }
        User user = userOptional.get();
        return new UserDTO(user.getUsername(), user.getEmailId(), user.getPhoneNumber());
    }

    public UserDTO updateUser(PutUserDTO putUserDTO) {
        Optional<User> userOptional = userRepository.findById(putUserDTO.getUserId());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User Doesn't Exist");
        }
        User user = userOptional.get();
        user.setUsername(putUserDTO.getUsername());
        user.setEmailId(putUserDTO.getEmailId());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getUsername(), savedUser.getEmailId(), savedUser.getPhoneNumber());
    }
}