package com.drnavalhabarbershop.resourceserver.web.dto;

import com.drnavalhabarbershop.resourceserver.domain.Person;
import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;

import java.util.List;

@Data
@Builder
public class BarberResponse {

    private String id;
    private Integer userType;
    private String about;
    private double currentRating;
    private Person person;
    private Binary profilePicture;
}
