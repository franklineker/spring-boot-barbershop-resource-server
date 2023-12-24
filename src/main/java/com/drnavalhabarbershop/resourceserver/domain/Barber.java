package com.drnavalhabarbershop.resourceserver.domain;

import br.com.drnavalha.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "barbers")
public class Barber{

    @Id
    private String id;

    private UserType userType;
    private String about;
    private List<RatingEntry> ratingsArray;
    private double currentRating;
    private Person person;
    private Binary profilePicture;
    @CreatedDate()
    private LocalDateTime createdDate;

}
