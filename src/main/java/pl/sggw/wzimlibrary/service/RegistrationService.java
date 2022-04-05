package pl.sggw.wzimlibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.model.dto.UserRegistrationDto;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final MailService mailService;
    private final ObjectMapper objectMapper;

    public void sendRegistrationMail(UserRegistrationDto userRegistrationDto, String baseUrl)
            throws JsonProcessingException, MessagingException {
        mailService.sendRegistrationMail(userRegistrationDto.getEmail(), baseUrl, encryptUser(userRegistrationDto));
    }

    public UserRegistrationDto getUserFromEncryptedMessage(String encryptedUser) throws IOException {
        return objectMapper.readValue(decryptUser(encryptedUser), UserRegistrationDto.class);
    }

    private String encryptUser(UserRegistrationDto userRegistrationDto) throws JsonProcessingException {
        String userJson = objectMapper.writeValueAsString(userRegistrationDto);
        return Base64.getEncoder().encodeToString(userJson.getBytes());
    }

    private String decryptUser(String encryptedUser) {
        return new String(Base64.getDecoder().decode(encryptedUser));
    }
}
