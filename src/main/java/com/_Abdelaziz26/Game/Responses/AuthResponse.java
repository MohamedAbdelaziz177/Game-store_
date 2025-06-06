package com._Abdelaziz26.Game.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String username;
    private String token;
    private String refreshToken;

    private Date expires;

    private boolean isAuthenticated;

    // if it errors
    private String message = null;
}
