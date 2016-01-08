package org.home.spring.mvc.common;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class User {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;
    @NotNull
    @Size(min = 2, max = 30)
    private String surname;

    public User() {
        // Need default constructor for unmarshalling object
        this(null, null);
    }

    public User(@Nullable String name, @Nullable String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Nullable
    public String getName() {
        // Need getter for JSP page
        return name;
    }

    @Nullable
    public String getSurname() {
        // Need getter for JSP page
        return surname;
    }

    public void setName(String name) {
        // Need setter for unmarshalling object
        this.name = name;
    }

    public void setSurname(String surname) {
        // Need setter for unmarshalling object
        this.surname = surname;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        User user = (User) other;
        return Objects.equals(name, user.name) && Objects.equals(surname, user.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
