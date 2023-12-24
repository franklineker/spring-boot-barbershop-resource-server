package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.mapper.ClientMapper;
import com.drnavalhabarbershop.resourceserver.service.ClientService;
import com.drnavalhabarbershop.resourceserver.web.dto.ClientRequest;
import jakarta.validation.Valid;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Validated
@CrossOrigin("*")
public class ClientController {
    @Autowired
    private ClientService clientService;
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Client createClient(@Valid @RequestBody ClientRequest request) throws IOException {
        return clientService.save(request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Client findClientById(@Valid @PathVariable String id){
        return clientService.findClientById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Client> findClients(){
        return clientService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Client updateClient(
            @Valid @PathVariable String id,
            @Valid @RequestBody ClientRequest request) throws Exception {
        return clientService.update(id, request);
    }

    @PutMapping(value = "updateImage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePicture(
            @Valid @PathVariable String id,
            @Valid @RequestParam(value = "file", required = false) Optional<MultipartFile> file) throws Exception {

        ClientRequest client = ClientMapper.toClientRequest(clientService.findClientById(id));
        if(file.isPresent()){
            client.setImage(new Binary(BsonBinarySubType.BINARY, file.get().getBytes()));
        }

        clientService.update(id, client);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Client deleteClient(@Valid @PathVariable String id) {
        return  clientService.delete(id);
    }

}
