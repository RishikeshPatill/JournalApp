package com.org.journalApp.scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTests {

    @Autowired
    private UserScheduler userScheduler;

    @Disabled
    @Test
    public void testFetchUserAndSendSentimentMail(){
        userScheduler.fetchUserAndSendSAMail();
    }
}
