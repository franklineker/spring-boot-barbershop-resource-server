package com.drnavalhabarbershop.resourceserver.web.dto;

import com.drnavalhabarbershop.resourceserver.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateRequest {
    private int userType;
    private Person person;
}
