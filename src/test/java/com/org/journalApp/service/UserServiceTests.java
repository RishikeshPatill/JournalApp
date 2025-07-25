package com.org.journalApp.service;

import com.org.journalApp.entity.User;
import com.org.journalApp.repository.UserRepository;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void testFindByUsername(){
        User user = userRepository.findByUsername("Virat");
        assertNotNull(user);
        assertTrue(!user.getJournalEntries().isEmpty());
    }


    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,2,4",
        "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }

    @Disabled
    @ParameterizedTest
    @ValueSource(strings = {                            //here we can pass csv, enum, value & custom or argument source
            "Rohit",
            "Virat",
            "Shreyas"
    })
    public void testFindByUsernameArgumentsSource(String username){
        assertNotNull(userRepository.findByUsername(username), "failed for "+username);
    }

    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){

    }

    //some important annotation of JUNIT Testing
    //BeforeEach & BeforeAll
    //AfterEach & AfterAll

    //we can use this method like to create csv in before all to test and in after all we can delete the csv
}
