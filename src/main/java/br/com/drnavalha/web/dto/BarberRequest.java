package br.com.drnavalha.web.dto;

import br.com.drnavalha.domain.Person;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarberRequest {

    private String id;
    @NotNull
    @Min(1)
    private Integer userType;
    private String about;
    private Person person;
    private Integer rating;
 //   private MultipartFile file;
    private Binary profilePicture;
}
