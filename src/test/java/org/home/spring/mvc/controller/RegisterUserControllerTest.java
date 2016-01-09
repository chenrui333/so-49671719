package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class RegisterUserControllerTest {
    @Mock private        Validator              validator;
    @Mock private        UsersRepository        usersRepository;
    @InjectMocks private RegisterUserController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(controller)
                .setValidator(validator)
                .build();
    }

    @Test
    public void shouldShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/user/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessUserRegistration() throws Exception {
        User user = new User("Jack", "Bauer");

        mockMvc.perform(post("/user/register")
                                .param("name", "Jack")
                                .param("surname", "Bauer"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/user/show/Jack"));

        verify(usersRepository).save(user);
    }

    @Test
    public void shouldRegistrationFormBeShown() throws Exception {
        when(validator.supports(any(Class.class))).thenReturn(true);
        doAnswer(invocation -> {
            Object secondArgument = invocation.getArguments()[1];
            Errors errors = (Errors) secondArgument;

            errors.reject("my error");

            return null;
        }).when(validator).validate(any(Object.class), any(Errors.class));

        mockMvc.perform(post("/user/register")
                                .param("name", "J")
                                .param("surname", "B"))
               .andExpect(status().isOk())
               .andExpect(view().name("registerForm"));

        verify(usersRepository, never()).save(any(User.class));
    }

    @Test
    public void shouldExceptionBeThrownWhenUserIsNotFound() throws Exception {
        when(usersRepository.findUserByName("Jack")).thenReturn(Optional.empty());

        mockMvc.perform(get("/user/show/Jack"))
               .andExpect(status().isNotFound())
               .andExpect(status().reason("User not found"));
    }

    @Test
    public void shouldProfileViewBeShownAndUserBeAddedToModelWhenUserIsFound() throws Exception {
        User user = new User("Jack", "Bauer");
        when(usersRepository.findUserByName("Jack")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/show/Jack"))
               .andExpect(status().isOk())
               .andExpect(view().name("profile"))
               .andExpect(model().attribute("user", user));
    }
}