package com.example.JournalApp;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Disabled
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @BeforeEach // before every test written this method initalize the code written inside
    public void Setup(){

    }


//    @BeforeAll  //before all tests written this method initalize the code written inside
//    public void Setup1(){
//
//    }


    @AfterEach  //after each tests written, this method initalize the code written inside
    public void Setup3(){

    }

//    @AfterAll  //after all tests written, this method initalize the code written inside
//    public void Setup4(){
//
//    }




//    @Disabled
//    @Test
    @ParameterizedTest
    @ValueSource(strings = {
            "Akansh",
            "Pranjal",
            "Suraj",
//            "Tanvi"
    })
    public void testFindByUserName(String userName) {
//        assertEquals(4,2+1);
        assertNotNull(userRepository.findByUserName(userName),"Failed for : "+userName);
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({ // can provide csv file from resourses folder for values
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a,int b,int expected){
        assertEquals(expected,a+b);
    }

}


