package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //выводим всех на view "index"
    @GetMapping
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    //выводим одного на view "user"
    @GetMapping("/{id}")
    public String showOneUser(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "users/user";
    }

    //получаем форму для добавления нового пользователя
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }
//создаем нового
    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users"; //redirect - переход по ссылке на /users
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users"; //redirect - переход по ссылке на /users
    }
}
