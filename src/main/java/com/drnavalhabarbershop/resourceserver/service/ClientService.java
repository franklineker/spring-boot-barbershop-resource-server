package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.exceptions.EmailChangeNotAllowedException;
import com.drnavalhabarbershop.resourceserver.mapper.ClientMapper;
import com.drnavalhabarbershop.resourceserver.repository.ClientRepository;
import com.drnavalhabarbershop.resourceserver.web.dto.ClientRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    private PasswordEncoder passwordEncoder;

    public Client save(ClientRequest request) throws IOException {

        if(Objects.equals(request.getImage(), null)) {
            byte[] array = Files.readAllBytes(Paths.get("src/main/resources/static/images/image-placeholder.png"));
            Binary defaultImage = new Binary(BsonBinarySubType.BINARY, array);
            request.setImage(defaultImage);
        }
        return clientRepository.save(ClientMapper.toClient(request));
    }

    public Client findClientById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found."));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client update(String id, ClientRequest request) throws Exception{

        checkClientExists(id);

        Client currentClient = findClientById(id);
        if(!Objects.equals(request.getPerson().getEmail(), currentClient.getPerson().getEmail())) {
            throw new EmailChangeNotAllowedException();
        }

        Binary image = request.getImage() == null ? currentClient.getImage() : request.getImage();

        Client newClient = ClientMapper.toClient(request);
        newClient.setId(id);
        newClient.setImage(image);

        return clientRepository.save(newClient);
    }

    public Client delete(String id) {

        checkClientExists(id);

        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Client Not Found."));

        clientRepository.deleteById(id);

        return client;
    }

    private void checkClientExists(String id) {
        if(clientRepository.findById(id).isEmpty()){
            throw new RuntimeException("Client not found.");
        }
    }

}
