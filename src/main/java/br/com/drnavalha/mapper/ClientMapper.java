package br.com.drnavalha.mapper;

import br.com.drnavalha.domain.Client;
import br.com.drnavalha.enums.UserType;
import br.com.drnavalha.web.dto.ClientRequest;
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
