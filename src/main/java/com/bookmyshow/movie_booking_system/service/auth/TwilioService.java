package com.bookmyshow.movie_booking_system.service.auth;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    private final Dotenv dotenv = Dotenv.load();

    private String accountSid;

    private String authToken;

    private String serviceSid;

    @PostConstruct
    public void initTwilio() {
        accountSid = dotenv.get("TWILIO_ACCOUNT_SID");
        authToken = dotenv.get("TWILIO_AUTH_TOKEN");
        serviceSid = dotenv.get("TWILIO_SERVICE_SID");
        System.out.println(accountSid);
        Twilio.init(accountSid, authToken);
    }

    public String sendOtp(String phoneNumber){
        try {
            Verification verification = Verification.creator(
                            serviceSid, "+91"+phoneNumber, "sms")  // Type: 'sms' for OTP sent via SMS
                    .create();
           return "OTP sent successfully!";
        } catch (Exception e) {
            return "Error sending otp: " +  e.getMessage();
        }
    }

    public boolean verifyOtp(String phoneNumber,String otpCode){
        try {
            // Verify OTP code
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                    .setTo("+91" + phoneNumber)
                    .setCode(otpCode)
                    .create();

            if ("approved".equals(verificationCheck.getStatus())) {
                System.out.println("otp verified");
                return true;
            } else {
                System.out.println("Invalid OTP!");
                return false;
            }
        } catch (Exception e) {
            throw new Error( "Error verifying OTP: " + e.getMessage());
        }
    }


}
