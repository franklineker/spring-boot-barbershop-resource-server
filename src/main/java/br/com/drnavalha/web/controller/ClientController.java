package br.com.drnavalha.web.controller;

import br.com.drnavalha.domain.Client;
import br.com.drnavalha.mapper.ClientMapper;
import br.com.drnavalha.service.ClientService;
import br.com.drnavalha.web.dto.ClientRequest;
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
    public Client createClient(@Valid @RequestBody ClientRequest request) {
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
    public Client updateClient(String id, ClientRequest request) {
        return clientService.update(id, request);
    }

    @PutMapping(value = "updateImage/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePicture(
            @Valid @PathVariable String id,
            @Valid @RequestParam("file") MultipartFile file) throws IOException {

        ClientRequest client = ClientMapper.toClientRequest(clientService.findClientById(id));
        client.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        clientService.update(id, client);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Client deleteClient(@Valid @PathVariable String id) {
        return  clientService.delete(id);
    }

}
