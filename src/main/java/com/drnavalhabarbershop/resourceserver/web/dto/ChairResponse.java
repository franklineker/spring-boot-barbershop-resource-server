package com.drnavalhabarbershop.resourceserver.web.dto;

import com.drnavalhabarbershop.resourceserver.domain.Barber;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ChairResponse {

    private Barber barber;
    private LocalDate startDate;
    private LocalDate endDate;

}
