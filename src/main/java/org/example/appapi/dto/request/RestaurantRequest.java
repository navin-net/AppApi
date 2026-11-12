package org.example.appapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantRequest {
    @NotBlank(message = "Restaurant name is required")
    @Size(max = 150)
    private String name;

    @Size(max = 30)
    private String phone;

    @Email(message = "Invalid email address")
    @Size(max = 150)
    private String email;

    private String address;

    @Size(max = 500)
    private String logoUrl;

    @Pattern(
            regexp = "^[A-Z]{3}$",
            message = "Currency must be a 3-letter uppercase code"
    )
    private String currency = "USD";

    private Boolean active = true;
}
