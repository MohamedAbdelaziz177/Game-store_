package com._Abdelaziz26.Game.Filters;

import com._Abdelaziz26.Game.Utility.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {


        if(request.getRequestURI().equalsIgnoreCase("/api/auth/**") ||
                request.getRequestURI().equalsIgnoreCase("/api/purchase/success") ||
                request.getRequestURI().equalsIgnoreCase("/api/purchase/cancel")
        )
        {
            filterChain.doFilter(request, response);
            return;
        }

        String fullToken = request.getHeader("Authorization");

        if(fullToken == null || !fullToken.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);
            return;
        }

        String token = fullToken.substring(7);

        String username = jwtService.extractUserName(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if(user == null)
            {
                filterChain.doFilter(request, response);
                return;
            }

            if(!jwtService.validateToken(token, user))
            {
                filterChain.doFilter(request, response);
                return;
            }

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            authToken.setDetails(user);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);

    }
}
