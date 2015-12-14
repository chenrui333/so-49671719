package org.home.spring.mvc.controller;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.home.spring.mvc.common.User;
import org.home.spring.mvc.common.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(JUnitParamsRunner.class)
@WebAppConfiguration
public class HomeControllerTest {
    @Mock
    private UsersRepository usersRepository;
    @InjectMocks
    private HomeController  controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldHomePageBeShowed() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/home.jsp"))
                .build();

        mockMvc.perform(get("/user/home")).andExpect(view().name("home"));
    }

    @Test
    @Parameters({"/users",
                 "/user/all2",
                 "/user/first"})
    @TestCaseName("Should users view be shown when user send request to path {0}")
    public void shouldUserListBeShown(String requestPath) throws Exception {
        when(usersRepository.findAllUsers()).thenReturn(expectedUserList());

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/users.jsp"))
                .build();

        mockMvc.perform(get(requestPath))
               .andExpect(view().name("users"))
               .andExpect(model().attributeExists("userList"))
               .andExpect(model().attribute("userList", hasItems(expectedUserList().toArray())));
    }

    private List<User> expectedUserList() {
        return range(1, 20)
                .mapToObj(value -> new User("Name" + value, "Surname" + value))
                .collect(Collectors.toList());
    }
}