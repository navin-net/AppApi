package org.example.appapi.dto.response;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RestaurantResponse {

    private Integer id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String logoUrl;

    private String currency;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}