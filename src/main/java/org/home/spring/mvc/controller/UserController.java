package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class UserController {
    private final UsersRepository usersRepository;

    @Inject
    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
