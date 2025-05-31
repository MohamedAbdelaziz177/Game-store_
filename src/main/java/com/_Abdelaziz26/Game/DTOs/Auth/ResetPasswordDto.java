package com._Abdelaziz26.Game.DTOs.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordDto {

    private String email;

    private String password;

    private String confirmPassword;

    private Long otp;
}
