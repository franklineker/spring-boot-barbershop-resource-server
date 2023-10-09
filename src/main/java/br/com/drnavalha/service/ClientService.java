package br.com.drnavalha.service;

import br.com.drnavalha.domain.Client;
import br.com.drnavalha.mapper.ClientMapper;
import br.com.drnavalha.repository.ClientRepository;
import br.com.drnavalha.web.dto.ClientRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ClientService {
    @Autowired
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public Client save(ClientRequest request) {
        return clientRepository.save(ClientMapper.toClient(request));
    }

    public Client findClientById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found."));
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client update(String id, ClientRequest request) {

        checkClientExists(id);

        Client client = ClientMapper.toClient((request));
        client.setId(id);

        return clientRepository.save(client);
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
