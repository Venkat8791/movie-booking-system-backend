package com.bookmyshow.movie_booking_system.service;

import com.bookmyshow.movie_booking_system.dto.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.dto.UserDTO;
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
}
