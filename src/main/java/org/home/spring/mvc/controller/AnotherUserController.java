package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AnotherUserController {
    private final UsersRepository usersRepository;

    @Inject
    public AnotherUserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @RequestMapping(value = "/users", method = GET)
    public List<User> users() {
        return usersRepository.findAllUsers();
    }
}
