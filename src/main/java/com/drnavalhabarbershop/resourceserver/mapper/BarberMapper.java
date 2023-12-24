package com.drnavalhabarbershop.resourceserver.mapper;

import br.com.drnavalha.enums.UserType;
import com.drnavalhabarbershop.resourceserver.domain.Barber;
import com.drnavalhabarbershop.resourceserver.domain.RatingEntry;
import com.drnavalhabarbershop.resourceserver.exceptions.EmailChangeNotAllowedException;
import com.drnavalhabarbershop.resourceserver.service.BarberService;
import com.drnavalhabarbershop.resourceserver.web.dto.BarberRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.BarberResponse;
import com.drnavalhabarbershop.resourceserver.web.dto.RateBarberRequest;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class BarberMapper {

    public static Barber toBarber(BarberRequest request) {

        return request != null ? Barber
                .builder()
                .userType(UserType.ofCode(request.getUserType()))
                .person(request.getPerson())
                .about(request.getAbout())
                .ratingsArray(new ArrayList<RatingEntry>())
                .createdDate(LocalDateTime.now())
                .build() : null;
    }

    public static BarberResponse toBarberResponse(Barber barber) {

        return BarberResponse.builder()
                .id(barber.getId())
                .person(barber.getPerson())
                .about(barber.getAbout())
                .currentRating(barber.getCurrentRating())
                .build();
    }

    public static BarberRequest toBarberRequest(Barber barber) throws IOException{

        return BarberRequest.builder()
                .id(barber.getId())
                .userType(barber.getUserType().getCode())
                .about(barber.getAbout())
                .person(barber.getPerson())
                .profilePicture(barber.getProfilePicture())
                .build();
    }

}
