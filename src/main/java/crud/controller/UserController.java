package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

@Autowired
    private UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserController() {
    }


    //выводим всех на view "index"
    @GetMapping(value = "/")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    //выводим одного на view "user"
    @GetMapping("/{id}")
    public String showOneUser(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "user";
    }

    //получаем форму для добавления нового пользователя
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

//создаем нового
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindResult) {
        String result = "redirect:/";
        if (bindResult.hasErrors()) {
            result = "new";
        } else {
            userService.saveUser(user);//redirect - переход по ссылке на /
        }
        return result;
    }

    //получаем форму на изменения
    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    //меняем данные пользователя
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/"; //redirect - переход по ссылке на /
    }

    //удаляем пользователя
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUserById(id);
        return "redirect:/"; //redirect - переход по ссылке на /
    }
}
