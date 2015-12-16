package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UsersRepository usersRepository;

    @Inject
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/all2", method = GET)
    public String users(Model model) {
        model.addAttribute(usersRepository.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/first", method = GET)
    public String firstUsers(Map model) {
        //noinspection unchecked
        model.put("userList", usersRepository.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/all", method = GET)
    public String users(Map model, @RequestParam("count") int count) {
        //noinspection unchecked
        model.put("userList", usersRepository.findFirstUsers(count));

        return "users";
    }

    @RequestMapping(value = "/{count}", method = GET)
    public String users2(Map model, @PathVariable("count") int count) {
        //noinspection unchecked
        model.put("userList", usersRepository.findFirstUsers(count));

        return "users";
    }
}
