package br.com.drnavalha.web.dto;

import br.com.drnavalha.domain.Person;
import lombok.Builder;
import lombok.Data;
import org.bson.types.Binary;

@Data
@Builder
public class BarberResponse {

    private String id;
    private Integer userType;
    private Person person;
    private String about;
    private Integer rating;
    private Binary profilePicture;
}
