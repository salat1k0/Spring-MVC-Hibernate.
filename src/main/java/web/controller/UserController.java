package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

    @GetMapping("/users/edit")
    public String editUser(@RequestParam Long id, Model model) {
        User user = userService.getUserById(id);
        List<User> users = userService.getAllUsers();

        model.addAttribute("user", user);
        model.addAttribute("users", users);
        return "user";
    }

    @PostMapping("/users/save")
    public String saveUser(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email) {
        User user = new User(firstName, lastName, email);
        userService.saveUser(user);
        return "redirect:/users";
    }

    @PostMapping("/users/update")
    public String updateUser(@RequestParam Long id,
                             @RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email) {
        User user = new User(firstName, lastName, email);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}