package com.med.MedConnect.Controller;
import com.med.MedConnect.Model.Address.Address;
import com.med.MedConnect.Model.Address.AddressRepo;
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

    @Autowired
    private AddressRepo addressRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList";
    }
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        List<Address> countries = addressRepository.findByParentIsNull();  // Fetch countries
        model.addAttribute("user", new User());  // Create new User object
        model.addAttribute("countries", countries);  // Add countries to model
        return "adduser";  // Return the view for adding a user
    }
    
    @PostMapping
    public String createUser(@ModelAttribute User user, @RequestParam("city") int cityId) {
        // Log the received cityId
        System.out.println("Received cityId: " + cityId);
    
        // Retrieve the Address (city)
        Address selectedAddress = addressRepository.findById(cityId).orElse(null);
        
        if (selectedAddress != null) {
            // Log the address selected
            System.out.println("Selected Address: " + selectedAddress.getName());
            user.setAddress(selectedAddress);  // Set the selected city (Address) to the user
        } else {
            System.out.println("Address not found for cityId: " + cityId);
        }
    
        userRepository.save(user);  // Save the user to the database
        return "redirect:/users";  // Redirect to the user list page after creation
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

    @GetMapping("/cities")
@ResponseBody
public List<Address> getCitiesByCountry(@RequestParam("countryId") int countryId) {
    return addressRepository.findByParent_addressId(countryId);  // Fetch cities for the selected country
}


}