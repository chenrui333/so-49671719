package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class RegisterUserControllerTest {
    @Mock private        UsersRepository        usersRepository;
    @InjectMocks private RegisterUserController controller;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(controller).build();
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
}