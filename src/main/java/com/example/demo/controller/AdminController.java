package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public AdminController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    //1
    @GetMapping
    public String getAdminInfo(Model model, Authentication authentication) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", new User());
        model.addAttribute("user1", userService.loadUserByUsername(authentication.getName()));
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin_info";
    }

    //2
    @PatchMapping("/update")
    public String update(User user,
                         @RequestParam("id") int id,
                         @RequestParam("name") String name,
                         @RequestParam("surname") String surname,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam("role") String[] roles) {
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);
        user.setRoles(roleService.getSetOfRolesFromList(roles));
        userService.update(user);
        return "redirect:/admin";
    }

    //3
    @PostMapping(value="/add")
    public String create(@ModelAttribute("user") User user, @RequestParam("role") String[] roles) {

        user.setRoles(roleService.getSetOfRolesFromList(roles));
        userService.add(user);
        return "redirect:/admin";
    }

    //4
    @DeleteMapping("/{username}")
    public String delete(@PathVariable("username") String username) {
        User userToDelete = userService.loadUserByUsername(username);
        userService.delete(userToDelete);
        return "redirect:/admin";
    }

}
