package com.bookmyshow.movie_booking_system.controller.AuthController;


import com.bookmyshow.movie_booking_system.service.UserService;
import com.bookmyshow.movie_booking_system.service.auth.JwtService;
import com.bookmyshow.movie_booking_system.service.auth.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mxmovies/v1/api/otp")
public class AuthenticationController {

    @Value("${twilio.verify.serviceSid}")
    private String serviceSid;

    @Autowired
    private UserService userService;

    @Autowired
    private TwilioService twilioService;

    @Autowired
    private JwtService jwtService;

//    @PostMapping("/send")
//    public ResponseEntity<String> sendOtp(@RequestParam("phoneNumber") String phoneNumber) {
//        String twilioResponse = twilioService.sendOtp(phoneNumber);
//        System.out.println(twilioResponse);
//        if (twilioResponse.equals("OTP sent successfully!")) {
//            return ResponseEntity.status(200).body(twilioResponse);
//        }
//        return ResponseEntity.status(500).body(twilioResponse);
//    }
//
//    @PostMapping("/verify")
//    public ResponseEntity<AuthResonseDTO> verifyOtp(@RequestParam("phoneNumber") String phoneNumber,
//                                                    @RequestParam("otpCode") String otpCode) {
//        boolean isVerified = twilioService.verifyOtp(phoneNumber, otpCode);
//        AuthResonseDTO authResonseDTO = new AuthResonseDTO(null, null, null, 90L);
//        if (isVerified) {
//            User user = userService.findOrCreateUser(phoneNumber);
//            String jwtToken = jwtService.generateToken(user);
//            authResonseDTO = new AuthResonseDTO(user.getId(), null, jwtToken, 90L);
//        }
//
//        return ResponseEntity.status(200).body(authResonseDTO);
//    }
}
