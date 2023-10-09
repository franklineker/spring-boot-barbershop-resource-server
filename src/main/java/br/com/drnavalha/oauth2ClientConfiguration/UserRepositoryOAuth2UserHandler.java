package br.com.drnavalha.oauth2ClientConfiguration;

import br.com.drnavalha.domain.User;
import br.com.drnavalha.mapper.UserMapper;
import br.com.drnavalha.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Optional;
import java.util.function.Consumer;
@AllArgsConstructor
@Slf4j
public class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final UserRepository userRepository;


    @Override
    public void accept(OAuth2User oAuth2User) {
        String oAuth2UserEmail = oAuth2User.getAttributes().get("email").toString();

        if (!Optional.ofNullable(userRepository.findByEmail(oAuth2UserEmail)).isPresent()) {
            User user = UserMapper.fromOauth2User(oAuth2User);
            log.info(user.toString());
            System.out.println(user);
            this.userRepository.save(user);
        }else {
            log.info("Bem-vindo {}", oAuth2User.getAttributes().get("given_name"));
            System.out.println("Bem-vindo " +  oAuth2User.getAttributes().get("name"));
        }
    }

}
