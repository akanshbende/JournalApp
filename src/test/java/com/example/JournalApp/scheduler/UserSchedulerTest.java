package com.example.JournalApp.scheduler;

import com.example.JournalApp.Scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class UserSchedulerTest {
    @Autowired
    private UserScheduler userScheduler;

    @Test
    public void fetchSaUsersAndSendMailTest(){
            userScheduler.fetchSaUsersAndSendMail();
    }
}
