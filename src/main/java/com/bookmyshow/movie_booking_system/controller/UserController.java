package com.bookmyshow.movie_booking_system.controller;


import com.bookmyshow.movie_booking_system.dto.request.LoginDTO;
import com.bookmyshow.movie_booking_system.dto.request.PostUserDTO;
import com.bookmyshow.movie_booking_system.dto.response.AuthResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.service.UserService;
import com.bookmyshow.movie_booking_system.service.auth.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mxmovies/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/api/signup")
    public ResponseEntity<PostUserResponseDTO> addUser(@RequestBody @Valid PostUserDTO userDTO) {
        PostUserResponseDTO postUserDTOResponse = userService.addUser(userDTO);
        return ResponseEntity.status(200).body(postUserDTOResponse);
    }

    @PostMapping("/api/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody @Valid LoginDTO loginDTO, HttpServletResponse response) {
        User user = userService.authenticateUser(loginDTO);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getId(), user.getEmail(), user.getFirstName());
        String authToken = jwtService.generateToken(loginDTO.getEmail());
        Cookie cookie = new Cookie("authToken", authToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(10 * 60 * 60);
        response.addCookie(cookie);
        return ResponseEntity.status(200).body(authResponseDTO);
    }

    @GetMapping("/api/current-user")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        String token = userService.extractJwtFromCookie(request);
        if (token != null && jwtService.validateToken(token)) {
            String email = jwtService.extractEmail(token);
            User user = userService.findUserByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getId(), user.getEmail(), user.getFirstName());
            return ResponseEntity.status(200).body(authResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
    }

    @PostMapping("/api/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/users/{userId}")
//    public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
//        UserDTO user = userService.getUserDetails(userId);
//        return ResponseEntity.status(200).body(user);
//    }
//
//    @PutMapping("/users/update-profile")
//    public ResponseEntity<UserDTO> updateUser(@RequestBody PutUserDTO user) {
//        System.out.println(user);
//        UserDTO userDTO = userService.updateUser(user);
//        return ResponseEntity.status(200).body(userDTO);
//    }
}
