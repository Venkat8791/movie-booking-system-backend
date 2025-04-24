package com.bookmyshow.movie_booking_system.controller;


import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.dto.request.UserDTO;
import com.bookmyshow.movie_booking_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mxmovies/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<PostUserResponseDTO> addUser(@RequestBody @Valid UserDTO userDTO){
        PostUserResponseDTO postUserDTOResponse = userService.addUser(userDTO);
        return ResponseEntity.status(200).body(postUserDTOResponse);
    }
}
