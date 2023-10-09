package br.com.drnavalha.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequest {

    private String clientID;
    private String clientName;
    private String barberID;
    private String barberName;
    private String orderID;
    private String orderTitle;
    private String date;
    private String hour;
}
