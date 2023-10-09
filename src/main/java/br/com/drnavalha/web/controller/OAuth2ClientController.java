package br.com.drnavalha.web.controller;

import br.com.drnavalha.domain.OAuth2Client;
import br.com.drnavalha.service.OAuth2ClientService;
import br.com.drnavalha.web.dto.OAuth2ClientRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
@Slf4j
public class OAuth2ClientController {
    @Autowired
    private OAuth2ClientService oAuth2ClientService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OAuth2Client createOAuth2Client(@RequestBody OAuth2ClientRequest request) {
        return oAuth2ClientService.create(request);
    }


}
