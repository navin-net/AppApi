package org.example.appapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {

    @NotNull(message = "Role id is required")
    private Integer roleId;

    @NotBlank(message = "Username is required")
    @Size(max = 80)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 100)
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(max = 150)
    private String fullName;

    @Size(max = 150)
    private String email;

    @Size(max = 30)
    private String phone;
}
