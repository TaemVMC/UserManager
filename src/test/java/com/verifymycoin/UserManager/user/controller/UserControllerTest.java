package com.verifymycoin.UserManager.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.verifymycoin.UserManager.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// controller - service - repository 연결 문제 없는지 확인하는 test
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserControllerTest.class)
@DisplayName("UserController Test")
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(
                new UserController(userService, modelMapper)
        ).addFilters(new CharacterEncodingFilter("UTF-8", true)).build();
    }

    @Test
    @DisplayName("signUpControllerTest")
    void signUpControllerTest() throws Exception {
        Object obj = new Object() {
            public final String code = "1234";
        };
        String content = objectMapper.writeValueAsString(obj);

        mockMvc.perform(post("/users/signup")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("signInControllerTest")
    void signInControllerTest() throws Exception {
        Object obj = new Object() {
            public final String code = "1234";
        };
        String content = objectMapper.writeValueAsString(obj);

        mockMvc.perform(post("/users/signin")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("getByIdControllerTest")
    void getByIdControllerTest() throws Exception {
        mockMvc.perform(get("/users/userInfo/123"))
                .andExpect(status().is(200))
                .andDo(print());
    }

    @Test
    @DisplayName("getAllUserInfoControllerTest")
    void getAllUserInfoControllerTest() throws Exception {
        mockMvc.perform(get("/users/allUserInfo"))
                .andExpect(status().is(200))
                .andDo(print());
    }



}