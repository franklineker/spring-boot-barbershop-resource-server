package br.com.drnavalha.domain;

import br.com.drnavalha.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private Integer rating;
    private Person person;
    private Binary profilePicture;
    private LocalDateTime createdDate;

}
