package com.med.MedConnect.Controller;


import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepo userRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody User loginUser) {
        // Find the user by email
        User user = userRepository.findByEmail(loginUser.getEmail());

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found.");
        }

        // Check if the password matches
        if (!user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }

        return ResponseEntity.ok("Login successful!");
    }
}