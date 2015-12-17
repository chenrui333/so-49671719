package org.home.spring.mvc.controller;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.home.spring.mvc.common.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.home.spring.mvc.controller.UserControllerTestHelper.expectedUserList;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(JUnitParamsRunner.class)
public class UserControllerTest {
    @Mock private        UsersRepository usersRepository;
    @InjectMocks private UserController  controller;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    @Parameters({"/user/all2",
                 "/user/first",
                 "/user/all?count=20",
                 "/user/20"})
    @TestCaseName("Should users view be shown when user send request to path {0}")
    public void shouldUserListBeShown(String requestPath) throws Exception {
        when(usersRepository.findAllUsers()).thenReturn(expectedUserList());
        when(usersRepository.findFirstUsers(anyInt())).thenReturn(expectedUserList());

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/users.jsp"))
                .build();

        mockMvc.perform(get(requestPath))
               .andExpect(status().isOk())
               .andExpect(view().name("users"))
               .andExpect(model().attributeExists("userList"))
               .andExpect(model().attribute("userList", hasItems(expectedUserList().toArray())));
    }
}