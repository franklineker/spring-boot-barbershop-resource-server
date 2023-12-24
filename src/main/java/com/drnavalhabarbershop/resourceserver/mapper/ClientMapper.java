package com.drnavalhabarbershop.resourceserver.mapper;

import br.com.drnavalha.enums.UserType;
import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.Person;
import com.drnavalhabarbershop.resourceserver.web.dto.ClientRequest;
import com.drnavalhabarbershop.resourceserver.web.dto.UserRequest;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class ClientMapper {

    public static Client toClient(ClientRequest request){


        return request != null ? Client
                .builder()
                .userType(UserType.ofCode(request.getUserType()))
                .image(request.getImage())
                .createdDate(LocalDateTime.now())
                .person(request.getPerson())
                .build() : null;
    }
    public static ClientRequest toClientRequest(Client client) {
        return client != null ? ClientRequest
                .builder()
                .userType(client.getUserType().getCode())
                .person(client.getPerson())
                .build() : null;
    }

}
