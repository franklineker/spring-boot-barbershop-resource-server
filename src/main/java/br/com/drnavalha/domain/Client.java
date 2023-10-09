package br.com.drnavalha.domain;

import br.com.drnavalha.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Convert;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "clients")
public class Client {

    @Id
    private String id;

    @CreatedDate()
    private LocalDateTime createdDate;
    @Convert()
    private UserType userType;
    private Binary image;
    private Person person;


}
