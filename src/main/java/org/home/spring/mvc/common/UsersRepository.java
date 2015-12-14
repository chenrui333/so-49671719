package org.home.spring.mvc.common;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

@Component
public class UsersRepository {
    @Nonnull
    public List<User> findAllUsers() {
        return findFirstUsers(20);
    }

    @Nonnull
    public List<User> findFirstUsers(int amount) {
        return range(1, amount)
                .mapToObj(value -> new User("Name" + value, "Surname" + value))
                .collect(Collectors.toList());
    }
}
