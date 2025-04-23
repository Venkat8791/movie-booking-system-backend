package com.bookmyshow.movie_booking_system.service.auth;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.verify.serviceSid}")
    private String serviceSid;



    @PostConstruct
    public void initTwilio() {
        System.out.println(authToken);
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
