package com.bookmyshow.movie_booking_system.controller;


import com.bookmyshow.movie_booking_system.dto.request.LoginDTO;
import com.bookmyshow.movie_booking_system.dto.request.PostUserDTO;
import com.bookmyshow.movie_booking_system.dto.request.PutUserRequestDTO;
import com.bookmyshow.movie_booking_system.dto.response.AuthResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.PostUserResponseDTO;
import com.bookmyshow.movie_booking_system.dto.response.PutUserResponseDTO;
import com.bookmyshow.movie_booking_system.entity.mysql.User;
import com.bookmyshow.movie_booking_system.exception.dto.UnAuthorizedException;
import com.bookmyshow.movie_booking_system.service.UserService;
import com.bookmyshow.movie_booking_system.service.auth.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/mxmovies/v1")
@Slf4j
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
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getPhoneNumber());
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
    public ResponseEntity<AuthResponseDTO> getCurrentUser(HttpServletRequest request) {
        log.info("fetching current user");
        String token = jwtService.extractJwtFromCookie(request);
        if (token == null || !jwtService.validateToken(token)) {
            throw new UnAuthorizedException("You are unauthorized. Please login to continue");
        }
        String email = jwtService.extractEmail(token);
        AuthResponseDTO authResponseDTO = userService.fetchCurrentUserByEmail(email);
        return ResponseEntity.status(200).body(authResponseDTO);
    }

    @PutMapping("/api/update-user")
    public ResponseEntity<PutUserResponseDTO> updateCurrentUser(HttpServletRequest request, @RequestBody PutUserRequestDTO user) {
        log.info("Updating current info");
        String token = jwtService.extractJwtFromCookie(request);
        if (token == null || !jwtService.validateToken(token)) {
            throw new UnAuthorizedException("You are unauthorized. Please login to continue");
        }
        PutUserResponseDTO responseDTO = userService.updateUser(user);
        return ResponseEntity.status(200).body(responseDTO);

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

}
