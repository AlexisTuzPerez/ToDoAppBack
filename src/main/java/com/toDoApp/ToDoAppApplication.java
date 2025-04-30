package com.toDoApp;

import com.toDoApp.auth.AuthResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAppApplication.class, args);
	}

}




// try every endpoint





//try exceptions

// Exceptions:
// Forbiden
// Bad request exception
// argument not valid
// Not found

// for exceptions, in the service we throw the exception

//   if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//		throw new IllegalArgumentException("Email already existssssssss");
//        }


//then, in the controller, we use the catch and e.getMessage() for getting the error



// try {
//
//AuthResponse authResponse = authService.register(request, response);
//
//            return ResponseEntity.ok("User registered successfully");
//        } catch (IllegalArgumentException e) {
//		return ResponseEntity.badRequest().body(e.getMessage());
//		}



// HATOS


// actuator