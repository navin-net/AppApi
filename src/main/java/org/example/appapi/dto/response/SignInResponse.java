package org.example.appapi.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    private String accessToken;
    private String tokenType;
    private String username;
}
