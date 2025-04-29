package com.bookmyshow.movie_booking_system.controller;


import com.bookmyshow.movie_booking_system.dto.request.PutUserDTO;
import com.bookmyshow.movie_booking_system.dto.request.UserDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mxmovies/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<PostUserResponseDTO> addUser(@RequestBody @Valid UserDTO userDTO) {
        PostUserResponseDTO postUserDTOResponse = userService.addUser(userDTO);
        return ResponseEntity.status(200).body(postUserDTOResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
        UserDTO user = userService.getUserDetails(userId);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/users/update-profile")
    public ResponseEntity<UserDTO> updateUser(@RequestBody PutUserDTO user) {
        System.out.println(user);
        UserDTO userDTO = userService.updateUser(user);
        return ResponseEntity.status(200).body(userDTO);
    }
}
