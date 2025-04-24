package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.dto.request.UserDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public PostUserResponseDTO addUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmailId(userDTO.getEmailId());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        User savedUser = userRepository.save(user);
        return new PostUserResponseDTO(savedUser.getId(),savedUser.getUsername(),savedUser.getEmailId(),savedUser.getPhoneNumber());

    }


    public User findOrCreateUser(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseGet(() -> {
                        User newUser = new User();
                        newUser.setPhoneNumber(phoneNumber);
                        return userRepository.save(newUser);
                });
    }

}
