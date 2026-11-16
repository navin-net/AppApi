package org.example.appapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {

    @NotBlank(message = "Username is required")
    @Size(max = 80)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 100)
    private String password;
}
