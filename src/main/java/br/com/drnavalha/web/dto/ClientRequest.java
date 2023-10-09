package br.com.drnavalha.web.dto;

import br.com.drnavalha.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequest {

    private Integer userType;
    private Binary image;
    private Person person;

}
