package br.com.drnavalha.service;

import br.com.drnavalha.domain.OAuth2Client;
import br.com.drnavalha.mapper.OAuth2ClientMapper;
import br.com.drnavalha.repository.OAuth2ClientRepository;
import br.com.drnavalha.web.dto.OAuth2ClientRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OAuth2ClientService implements RegisteredClientRepository {

    private OAuth2ClientRepository oAuth2ClientRepository;
    private final PasswordEncoder passwordEncoder;

    public OAuth2Client create(OAuth2ClientRequest request) {
        OAuth2Client oAuth2Client = OAuth2ClientMapper.toOAuth2Client(request);
        oAuth2Client.setClientSecret(passwordEncoder.encode(request.getClientSecret()));

        return  oAuth2ClientRepository.save(oAuth2Client);
    }
    @Override
    public void save(RegisteredClient registeredClient) {

    }

    @Override
    public RegisteredClient findById(String id) {
        OAuth2Client oAuth2Client = oAuth2ClientRepository.findByClientId(id)
                .orElseThrow(() -> new RuntimeException("Client Not Found"));
        return OAuth2ClientMapper.toRegisteredClient(oAuth2Client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        OAuth2Client oAuth2Client = oAuth2ClientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client Not Found"));
        return OAuth2ClientMapper.toRegisteredClient(oAuth2Client);
    }


}
