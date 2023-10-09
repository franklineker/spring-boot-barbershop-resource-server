package br.com.drnavalha.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "oAuth2Client")
public class OAuth2Client {

    @Id
    private String id;
    private String clientId;
    private Instant clientIdIssuedAt;
    private String clientSecret;
    private Set<AuthorizationGrantType> authorizationGrantTypes;
    private Set<ClientAuthenticationMethod> authenticationMethods;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private boolean requireProofKey;


}
