package com.verifymycoin.UserManager.user.service;

import com.verifymycoin.UserManager.GoogleAuth.GoogleOauth;
import com.verifymycoin.UserManager.user.domain.User;
import com.verifymycoin.UserManager.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private GoogleOauth googleOauth;
    @MockBean private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(googleOauth,userRepository);
    }

    @Test
    void signin() {

    }

    @Test
    void existsBySub() {
//        save
        User user = User.builder()
                .userId("1234")
                .name("test")
                .sub("1234567")
                .familyName("familyNameTest")
                .picture("/picture/url")
                .build();
        assertThat(user.getUserId(), is(equalTo("1234")));
        userService.findByUserId("1234");
    }


    @Test
    void save() {
//        save
        User user = User.builder()
                .userId("1234")
                .name("test")
                .sub("1234567")
                .familyName("familyNameTest")
                .picture("/picture/url")
                .build();
        assertThat(user.getUserId(), is(equalTo("1234")));
        userService.save(user);
        verify(userRepository).save(user);
    }


}