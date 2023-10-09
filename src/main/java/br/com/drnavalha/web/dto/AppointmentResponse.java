package br.com.drnavalha.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentResponse {

    private String clientName;
    private String barberName;
    private String orderTitle;
    private String date;
    private String hour;
}
