package com.bravewood.safelenderbatchprocessing.payroll.request;


import com.bravewood.safelenderbatchprocessing.payroll.domain.Role;
import com.bravewood.safelenderbatchprocessing.payroll.request.ERole;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email(message = "Email is not valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    private Set<String> role;



}
