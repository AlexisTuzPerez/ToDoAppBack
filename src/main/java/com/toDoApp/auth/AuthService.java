package com.toDoApp.auth;


import com.toDoApp.exceptions.customExceptions.BadRequestException;
import com.toDoApp.user.User;
import com.toDoApp.user.Role;
import com.toDoApp.config.JwtService;
import com.toDoApp.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;


    // In AuthenticationService.java
    public AuthResponse register(RegisterRequest request,HttpServletResponse response) {


        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();



        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BadRequestException("Email already existssssssss");
        }


        userRepository.save(user);


        var jwtToken = jwtService.generateToken(user);


        AuthResponse authResponse = AuthResponse.builder().accessToken(jwtToken).build();


        createCookie(response , authResponse);




        return authResponse;
    }

    public AuthResponse authenticate(AuthRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );



        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("User not found");
                });

        var jwtToken = jwtService.generateToken(user);
        AuthResponse authResponse = AuthResponse.builder().accessToken(jwtToken).build();

        createCookie(response , authResponse);



        return authResponse;
    }


    public void createCookie(HttpServletResponse response, AuthResponse authResponse) {

        Cookie cookie = new Cookie("toDoAppCookie", authResponse.getAccessToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(86400);
        cookie.setAttribute("SameSite", "Lax");
        response.addCookie(cookie);


        response.addCookie(cookie);

    }





}
