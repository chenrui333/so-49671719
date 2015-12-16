package org.home.spring.mvc.controller;

import org.home.spring.mvc.common.UsersRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.home.spring.mvc.controller.UserControllerTestHelper.expectedUserList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AnotherUserControllerTest {
    @Mock
    private UsersRepository       usersRepository;
    @InjectMocks
    private AnotherUserController controller;

    @Test
    public void shouldUserListBeShown() throws Exception {
        when(usersRepository.findAllUsers()).thenReturn(expectedUserList());

        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/users.jsp"))
                .build();

        mockMvc.perform(get("/users"))
               .andExpect(view().name("users"))
               .andExpect(model().attributeExists("userList"))
               .andExpect(model().attribute("userList", hasItems(expectedUserList().toArray())));
    }
}