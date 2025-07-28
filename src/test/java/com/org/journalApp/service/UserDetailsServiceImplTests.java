package com.org.journalApp.service;

import com.org.journalApp.entity.User;
import com.org.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import static org.mockito.Mockito.*;

// if we don't want to load the application context then in that case we use mocking and
// if we just want to mock some beans then we use this annotation, but in case if we want
// to mock all the dependency of the class of the test then we use @InjectMocks with @Mock.
// we use @InjectMocks in place of autowired not on class
//@SpringBootTest
public class UserDetailsServiceImplTests {

    // after using the spring boot test and mock bean together our application context starts which is a load
    // so this is not full mocking, so for that we use @InjectMocks along with @Mock for complete mocking
    //@Autowired
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    // we have used the above annotation so that when the above dependency gets autowired
    // then in that case this mock bean also get injected with the above instance
    //@MockBean
    @Mock
    private UserRepository userRepository;

    // we use Before each to initialize the @Mock annotated instance without this Mocking fails
    // now in our case inject mocks create the instance for the service, and after the below method call
    // the service instance has access to all the mocks, annotated with the mock instance
    @Disabled
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Disabled
    @Test
    public void loadByUsernameTest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("mock_user").password("mock_user").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("mock_user");
    }
}
