package pl.sggw.wzimlibrary.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void sendRegistrationMail(String to, String baseUrl, String encryptedUser) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject("WZiM Library - rejestracja");
        String messageText = "<html><body><h1>Witaj w WZiM Library!</h1><p>Twoje konto zostało utworzone. Aby aktywować konto, kliknij w poniższy link:</p><p><a href=\"" + baseUrl + "/activateAccount?user=" + encryptedUser + "\">Aktywuj konto</a></p></body></html>";
        helper.setText(messageText, true);
        mailSender.send(message);

    }
}

