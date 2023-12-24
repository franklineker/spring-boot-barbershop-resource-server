package com.drnavalhabarbershop.resourceserver.mapper;


import com.drnavalhabarbershop.resourceserver.domain.Appointment;
import com.drnavalhabarbershop.resourceserver.web.dto.AppointmentRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.AppointmentResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class AppointmentMapper {
    public static Appointment toAppointment(AppointmentRequest request) {
        return request != null ? Appointment
                .builder()
//                .order(request.getOrder())
//                .client(request.getClient())
//                .barber(request.getBarber())
                .date(request.getDate())
                .createdDate(LocalDateTime.now())
                .build() : null;
    }

    public static AppointmentResponse toAppointmentResponse(Appointment appointment) {

        return AppointmentResponse.builder()
                .client(appointment.getClient())
                .order(appointment.getOrder())
                .barber(appointment.getBarber())
                .date(appointment.getDate())
                .build();
    }
}
