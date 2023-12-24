package com.drnavalhabarbershop.resourceserver.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {

    private String email;
    private List<String> roles;
}
