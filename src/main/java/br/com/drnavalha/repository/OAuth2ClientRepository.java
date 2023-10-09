package br.com.drnavalha.repository;

import br.com.drnavalha.domain.OAuth2Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuth2ClientRepository extends MongoRepository<OAuth2Client, String> {
    Optional<OAuth2Client> findByClientId(String clientId);
}
