package br.com.drnavalha.mapper;

import br.com.drnavalha.domain.OAuth2Client;
import br.com.drnavalha.web.dto.OAuth2ClientRequest;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.util.Date;

@NoArgsConstructor
public class OAuth2ClientMapper {

    public static OAuth2Client toOAuth2Client(OAuth2ClientRequest request) {

        return OAuth2Client.builder()
                .clientId(request.getClientId())
                .clientSecret(request.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .authenticationMethods(request.getAuthenticationMethods())
                .authorizationGrantTypes(request.getAuthorizationGrantTypes())
                .redirectUris(request.getRedirectUris())
                .scopes(request.getScopes())
                .requireProofKey(request.isRequireProofKey())
                .build();
    }

    public static RegisteredClient toRegisteredClient(OAuth2Client client) {
        RegisteredClient.Builder builder = RegisteredClient
                .withId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientIdIssuedAt(new Date().toInstant())
                .clientAuthenticationMethods(am -> am.addAll(client.getAuthenticationMethods()))
                .authorizationGrantTypes(agt -> agt.addAll(client.getAuthorizationGrantTypes()))
                .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                .scopes(sc -> sc.addAll(client.getScopes()))
                .clientSettings(ClientSettings
                        .builder()
                        .requireProofKey(client.isRequireProofKey())
                        .requireAuthorizationConsent(true)
                        .build()
                );
        return builder.build();
    }
}
