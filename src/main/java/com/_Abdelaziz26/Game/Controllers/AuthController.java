package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Auth.ConfirmEmailDto;
import com._Abdelaziz26.Game.DTOs.Auth.LoginDto;
import com._Abdelaziz26.Game.DTOs.Auth.RegisterDto;
import com._Abdelaziz26.Game.DTOs.Auth.ResetPasswordDto;
import com._Abdelaziz26.Game.Exceptions.CodeNotValidException;
import com._Abdelaziz26.Game.Exceptions.PasswordsNotMatchedException;
import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Responses.AuthResponse;
import com._Abdelaziz26.Game.Responses.TokenResponse;
import com._Abdelaziz26.Game.Services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> Register(@RequestBody RegisterDto registerDto) {

       AuthResponse authResponse = authService.register(registerDto);

       if(!authResponse.isAuthenticated())
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);

       return ResponseEntity.status(HttpStatus.OK).body(authResponse);

    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(HttpServletResponse response, @RequestBody LoginDto loginDto) {

        AuthResponse authResponse = authService.login(response, loginDto);

        if(!authResponse.isAuthenticated())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(authResponse);

        return ResponseEntity.status(HttpStatus.OK).body(authResponse);

    }


    @GetMapping("/refresh-token")
    public ResponseEntity<TokenResponse> RefreshToken(HttpServletRequest request, HttpServletResponse response) {

        // get Ref token from cookie / authHeader / Request param

        TokenResponse tokenResponse =  authService.refreshToken(request, response);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);

    }


    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> ResetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {

        ApiResponse<String> res = new ApiResponse<>();

        authService.resetPassword(resetPasswordDto);
        res.setSuccess(true);
        res.setData("Password reset successfully");

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    @PostMapping("/confirm-email")
    public ResponseEntity<ApiResponse<String>> ConfirmEmail(@RequestBody ConfirmEmailDto confirmEmailDto) {

        ApiResponse<String> res = new ApiResponse<>();

        authService.confirmEmail(confirmEmailDto);
        res.setSuccess(true);
        res.setData("Email confirmed successfully");

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

    @PostMapping("/resend-confirmation-code")
    public ResponseEntity<ApiResponse<String>> ResendConfirmationCode(@RequestBody Map<String, String>mp /*must be in req body*/) {

        ApiResponse<String> res = new ApiResponse<>();

        authService.sendOtpToUser(mp.get("email"));

        res.setSuccess(true);
        res.setData("Confirmation code sent successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }


}
