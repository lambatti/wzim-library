package pl.sggw.wzimlibrary.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sggw.wzimlibrary.exception.UserDecryptionException;
import pl.sggw.wzimlibrary.model.dto.user.UserRegistrationDto;
import pl.sggw.wzimlibrary.util.MailUtil;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final MailUtil mailUtil;
    private final ObjectMapper objectMapper;

    public void sendRegistrationMail(UserRegistrationDto userRegistrationDto, String baseUrl)
            throws JsonProcessingException, MessagingException {
        mailUtil.sendRegistrationMail(userRegistrationDto.getEmail(), baseUrl, encryptUser(userRegistrationDto));
    }

    public UserRegistrationDto getUserFromEncryptedMessage(String encryptedUser) throws IOException, UserDecryptionException {
        return objectMapper.readValue(decryptUser(encryptedUser), UserRegistrationDto.class);
    }

    private String encryptUser(UserRegistrationDto userRegistrationDto) throws JsonProcessingException {
        String userJson = objectMapper.writeValueAsString(userRegistrationDto);
        return Base64.getEncoder().encodeToString(userJson.getBytes());
    }

    private String decryptUser(String encryptedUser) throws UserDecryptionException {
        String decryptedUser;
        try {
            decryptedUser = Arrays.toString(Base64.getDecoder().decode(encryptedUser));
        } catch (IllegalArgumentException e) {
            throw new UserDecryptionException("There was an error while decrypting the user.");
        }
        return decryptedUser;
    }
}
