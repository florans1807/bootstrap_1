package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getAdminInfo(@ModelAttribute("modalUser") User user, Model model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin_info";
    }

    //2
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "show";
    }

    //3
    @DeleteMapping("/{username}")
    public String delete(@PathVariable("username") String username) {
        User userToDelete = userService.loadUserByUsername(username);
        userService.delete(userToDelete);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "new";
    }

    //8
    @PostMapping(value="/add")
    public String create(@ModelAttribute("user") User user
            , @RequestParam("role") String[] roles) {

        user.setRoles(roleService.getSetRole(roles));
        userService.add(user);
        return "redirect:/admin";
    }

    //4
    @GetMapping("/{id}/edit")
    public String edit(Model model1, @PathVariable("id") int id) {
        model1.addAttribute("user", userService.get(id));
        model1.addAttribute("allRoles", roleService.getAllRoles());
        return "edit";
    }

    @PostMapping("/update")    //{id}
    public String update(@ModelAttribute("user") User user
            , @RequestParam("role") String[] roles) {

        user.setRoles(roleService.getSetRole(roles));
        userService.update(user);
        return "redirect:/admin";
    }
}
