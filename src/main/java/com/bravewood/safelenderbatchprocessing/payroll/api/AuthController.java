package com.bravewood.safelenderbatchprocessing.payroll.api;

import com.bravewood.safelenderbatchprocessing.payroll.request.LoginRequest;
import com.bravewood.safelenderbatchprocessing.payroll.service.LoginService;
import com.bravewood.safelenderbatchprocessing.payroll.request.SignUpRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/signing")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.authenticateUser(loginRequest));


    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        return ResponseEntity.ok(loginService.registerUser(signUpRequest));
//    }






}
