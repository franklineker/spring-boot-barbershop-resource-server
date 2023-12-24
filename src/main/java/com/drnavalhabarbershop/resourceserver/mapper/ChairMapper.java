package com.drnavalhabarbershop.resourceserver.mapper;

import com.drnavalhabarbershop.resourceserver.domain.Chair;
import com.drnavalhabarbershop.resourceserver.web.dto.ChairRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.ChairResponse;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class ChairMapper {

    public static Chair toChair(ChairRequest request) {

        return request != null ? Chair
                .builder()
                .barber(request.getBarber())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build() : null;
        }

//    public static ChairResponse toChairResponse(Chair chair) {
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        LocalDate startDate = LocalDate.parse(chair.getStartDate().format(formatter));
//        LocalDate endDate = LocalDate.parse(chair.getEndDate(), formatter);
//
//        return chair != null ? ChairResponse
//                .builder()
//                .barber(chair.getBarber())
//                .startDate(startDate)
//                .endDate(endDate)
//                .build() : null;
//    }
}
