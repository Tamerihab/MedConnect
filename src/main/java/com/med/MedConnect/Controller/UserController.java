package com.med.MedConnect.Controller;

import com.med.MedConnect.Model.AddressRepo;
import com.med.MedConnect.Model.Address;
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
    
    @Autowired
    private AddressRepo addressRepository;
    


    @GetMapping("/cities")
    @ResponseBody
    public List<Address> getCitiesByCountry(@RequestParam("countryId") int countryId) {
        List<Address> cities = addressRepository.findByParent_addressId(countryId); // Fetch cities using parent_id (countryId)
        return cities;
    }

    
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        // Fetch countries (parentId = null or 0)
        List<Address> countries = addressRepository.findByParentIsNull();
        model.addAttribute("user", new User()); // Create a new User object to bind to the form
        model.addAttribute("countries", countries); // Add countries for selection
        return "addUser"; // The Thymeleaf view name (addUser.html)
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
    public String createUser(@ModelAttribute User user, @RequestParam("city") int cityId) {
        // Fetch the Address (City) based on the selected cityId
        Address selectedAddress = addressRepository.findById(cityId).orElse(null);
        
        if (selectedAddress != null) {
            user.setAddress(selectedAddress);  // Set the selected city (Address) to the user
        }
    
        userRepository.save(user);  // Save the user to the database
        return "redirect:/users";  // Redirect to the user list page after creation
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
