package br.com.drnavalha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String id;

    private String clientID;
    private String clientName;
    private String barberID;
    private String barberName;
    private String orderID;
    private String orderTitle;
    private String date;
    private String hour;

}
