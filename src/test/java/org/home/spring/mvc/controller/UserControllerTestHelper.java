package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class UserControllerTestHelper {
    @Nonnull
    public static List<User> expectedUserList() {
        return range(1, 20)
                .mapToObj(value -> new User("Name" + value, "Surname" + value))
                .collect(Collectors.toList());
    }
}
