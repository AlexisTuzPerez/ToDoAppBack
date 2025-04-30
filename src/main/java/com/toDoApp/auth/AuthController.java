package com.toDoApp.auth;



import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


//    @PostMapping("/register")
//    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
//        try {
//            return ResponseEntity.ok(authService.register(request));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage())); // Handle the error
//        }
//    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request, HttpServletResponse response) {
        try {

            AuthResponse authResponse = authService.register(request, response);

            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request, HttpServletResponse response) {


        try{

            AuthResponse authResponse = authService.authenticate(request, response);
            return ResponseEntity.ok("Authentication successful");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Something went wrong:" + e.getMessage());
        }


    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("toDoAppCookie", null);
        cookie.setMaxAge(0); // Delete the cookie
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("Logout successful");
    }



}



