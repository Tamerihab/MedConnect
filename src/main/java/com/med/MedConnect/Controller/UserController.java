package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.User.User;
import com.med.MedConnect.Model.User.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }

    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping
    public String createUser(@Valid @ModelAttribute User user,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "addUser";
        }

        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable int id,
                             @Valid @ModelAttribute User userDetails,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "editUser";
        }

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setNationalID(userDetails.getNationalID());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setVolunteer(userDetails.isVolunteer());

        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable int id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
        return "redirect:/users";
    }
}