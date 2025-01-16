package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User()); // Create a new User object to bind to the form
        return "addUser"; // The name of the Thymeleaf view (addUser.html)
    }

    // Get all users and render view (JSP or Thymeleaf)
    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users); // Add the list to the model
        return "userList"; // Return the view name (e.g., userList.html or userList.jsp)
    }

    // Get a user by ID and render the user details view
    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            model.addAttribute("user", user); // Add the user to the model
            return "userDetails"; // View for user details
        }
        return "error"; // View for error if user not found
    }

    // Create a new user and redirect to the list of users
    @PostMapping
    public String createUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users"; // Redirect to the list of users after creating a new user
    }

    // Update an existing user and return the updated user view
    @PutMapping("/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute User userDetails, Model model) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setNationalID(userDetails.getNationalID());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setVolunteer(userDetails.isVolunteer());
            userRepository.save(user);
            model.addAttribute("user", user); // Add the updated user to the model
            return "userDetails"; // Return the updated user details view
        }).orElse("error"); // If user not found, return error page
    }

    // Delete a user and redirect to the list of users
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return "redirect:/users"; // Redirect to the list of users after deletion
        }).orElse("error"); // If user not found, return error page
    }
}
