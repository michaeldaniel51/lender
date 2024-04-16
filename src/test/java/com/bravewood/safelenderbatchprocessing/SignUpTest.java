package com.bravewood.safelenderbatchprocessing;


import com.bravewood.safelenderbatchprocessing.payroll.request.SignUpRequest;
import com.bravewood.safelenderbatchprocessing.payroll.response.MessageResponse;
import com.bravewood.safelenderbatchprocessing.payroll.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Slf4j
public class SignUpTest {

    public SignUpTest() {
    }

    @Autowired
    private LoginService loginService;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
        log.info("run after each test");
    }


    @Test
    public void testSignUp() {

        Set<String> roles = new HashSet<>();
        roles.add("user");
        roles.add("admin");
        roles.add("mod");

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("olamilekan@bravewood.ng");
        signUpRequest.setUsername("lekan");
        signUpRequest.setPassword("password");
        signUpRequest.setRole(roles);

        MessageResponse messageResponse = loginService.registerUser(signUpRequest);

        log.info(messageResponse.getMessage());

Assertions.assertThat(messageResponse.getMessage()).isEqualTo("User registered successfully!");

    }



}
