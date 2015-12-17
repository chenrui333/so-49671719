package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/user")
public class RegisterUserController {
    private final UsersRepository usersRepository;

    @Inject
    public RegisterUserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(User user) {
        usersRepository.save(user);

        return "redirect:/user/show/" + user.getName();
    }

    @RequestMapping(value = "/show/{username}", method = GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        usersRepository.findUserByName(username)
                       .ifPresent(model::addAttribute);

        return "profile";
    }
}
