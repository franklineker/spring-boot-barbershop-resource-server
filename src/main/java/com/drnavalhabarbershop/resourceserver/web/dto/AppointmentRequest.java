package com.drnavalhabarbershop.resourceserver.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentRequest {

    private String barberId;
    private String clientId;
    private String orderId;
    private Date date;
}
