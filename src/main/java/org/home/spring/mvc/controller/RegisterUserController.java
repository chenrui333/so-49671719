package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.NOT_FOUND;
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
    public String processRegistration(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        usersRepository.save(user);

        return "redirect:/user/show/" + user.getName();
    }

    @RequestMapping(value = "/show/{username}", method = GET)
    public String showUserProfile(@PathVariable String username, Model model) {
        User user = usersRepository.findUserByName(username)
                                   .orElseThrow(NotFoundUserException::new);
        model.addAttribute(user);

        return "profile";
    }

    @ResponseStatus(value = NOT_FOUND, reason = "User not found")
    private class NotFoundUserException extends RuntimeException {}
}
