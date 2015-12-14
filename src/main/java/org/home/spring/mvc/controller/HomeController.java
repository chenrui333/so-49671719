package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {
    private final UsersRepository usersRepository;

    @Inject
    public HomeController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/user/home", method = GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/users", method = GET)
    public List<User> users() {
        return usersRepository.findAllUsers();
    }

    @RequestMapping(value = "/user/all2", method = GET)
    public String users2(Model model) {
        model.addAttribute(usersRepository.findAllUsers());

        return "users";
    }

    @RequestMapping(value = "/user/first", method = GET)
    public String firstUsers(Map model) {
        //noinspection unchecked
        model.put("userList", usersRepository.findAllUsers());

        return "users";
    }
}
