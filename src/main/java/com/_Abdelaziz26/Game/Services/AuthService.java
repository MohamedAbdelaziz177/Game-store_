package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Auth.ConfirmEmailDto;
import com._Abdelaziz26.Game.DTOs.Auth.LoginDto;
import com._Abdelaziz26.Game.DTOs.Auth.RegisterDto;
import com._Abdelaziz26.Game.DTOs.Auth.ResetPasswordDto;
import com._Abdelaziz26.Game.Exceptions.CodeNotValidException;
import com._Abdelaziz26.Game.Exceptions.PasswordsNotMatchedException;
import com._Abdelaziz26.Game.Model.Role;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.RoleRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import com._Abdelaziz26.Game.Responses.AuthResponse;
import com._Abdelaziz26.Game.Responses.TokenResponse;
import com._Abdelaziz26.Game.Utility.JwtService;
import com._Abdelaziz26.Game.Utility.MailService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Value("${app.env}")
    private String APP_ENV;


    public AuthResponse login(HttpServletResponse response, LoginDto loginDto) {

        Authentication authToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());


        Authentication authRes = authenticationManager.authenticate(authToken);

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());

        User user = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() ->
                new NoSuchElementException("No such user found"));

        if(APP_ENV.equalsIgnoreCase("production"))
            if(!user.isVerified())
                return AuthResponse.builder()
                        .message("Please verify your email")
                        .isAuthenticated(false).build();


        TokenResponse tokenResponse = jwtService.getTokens(user);

        setRefreshTokenInCookie(response, tokenResponse.getRefreshToken());

        return AuthResponse.builder()
                .token(tokenResponse.getAccessToken())
                .username(user.getUsername())
                .refreshToken(tokenResponse.getRefreshToken())
                .isAuthenticated(true)
                .expires(jwtService.getExpiry())
                .build();


    }

    public AuthResponse register(RegisterDto registerDto) {

        Optional<User> user1 = userRepository.findByEmail(registerDto.getEmail());

        if(user1.isPresent())
            throw new AuthenticationServiceException("User already exists");

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow();

        User user = User.builder()
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .roles(List.of(role))
                .build();


        userRepository.save(user);

        sendOtpToUser(registerDto.getEmail());

        return AuthResponse.builder()
                .message("User Registered Successfully - plz check ur email for confirmation")
                .isAuthenticated(true)
                .build();


    }

    public TokenResponse refreshToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = getRefreshTokenFromCookie(request);

        TokenResponse tokenResponse =  jwtService.refreshToken(refreshToken);

        if(!tokenResponse.isSuccess())
            throw new NoSuchElementException("No such token found");

        setRefreshTokenInCookie(response, tokenResponse.getRefreshToken());

        return tokenResponse;

    }

    private Long generateOtp()
    {
        return new Random().nextLong(100000, 199999);
    }

    public void sendOtpToUser(User user){

        Long otp = generateOtp();
        mailService.sendSimpleMail(user.getEmail(), "Confirmation Code", otp.toString());

        user.setVerificationToken(otp);
        user.setVerificationCodeExpiryDate(new Date(System.currentTimeMillis() + 86400000));
        user.setVerified(false);

        //متنساش تسيف بعد كدا الله يرضى عنك
        userRepository.save(user);
    }

    public void sendOtpToUser(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("No such user found"));
        sendOtpToUser(user);
    }

    public void confirmEmail(ConfirmEmailDto confirmEmailDto)
    {
        User user = userRepository.findByEmail(confirmEmailDto.getEmail()).orElseThrow(() ->
                new NoSuchElementException("No such user found"));


        if(!user.getVerificationToken().equals(confirmEmailDto.getOtp()) ||
                user.getVerificationCodeExpiryDate().getTime() < System.currentTimeMillis())
            throw new CodeNotValidException("Code not valid");

        user.setVerified(true);
        user.setVerificationToken(0L);

        userRepository.save(user);
    }

    public void resetPassword(ResetPasswordDto resetPasswordDto)
    {
        User user = userRepository.findByEmail(resetPasswordDto.getEmail()).orElseThrow(() ->
                new NoSuchElementException("No such user found"));

        if((!Objects.equals(user.getVerificationToken(), resetPasswordDto.getOtp())) ||
                user.getVerificationCodeExpiryDate().getTime() < System.currentTimeMillis())
            throw new CodeNotValidException("Code not valid");


        if(!resetPasswordDto.getConfirmPassword().equals(resetPasswordDto.getPassword()))
            throw new PasswordsNotMatchedException("password and confirm password fields are not the same");

        user.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));
        user.setVerificationToken(0L);

        userRepository.save(user);

    }

    private void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken)
    {
        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/api/auth/refresh-token");
        cookie.setMaxAge(3600 * 24 * 15);

        response.addCookie(cookie);
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request)
    {
        for(Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("refreshToken")) {
                return cookie.getValue();
            }
        }

        return null;
    }
}
