package be.ucll.gip5.controller;

import be.ucll.gip5.config.EmailConfig;
import be.ucll.gip5.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private EmailConfig emailConfig;

    @PostMapping(value = "/send/{HouseId}")
    public ResponseEntity sendEmail(@PathVariable Long houseId) {
        try {
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(this.emailConfig.getHost());
            javaMailSender.setPort(this.emailConfig.getPort());
            javaMailSender.setUsername(this.emailConfig.getUsername());
            javaMailSender.setPassword(this.emailConfig.getPassword());

            //list omzetten naar array voor setTo
            List<User> list = mailService.getEmailsForNewHome(houseId);
            String[] array = list.toArray(new String[list.size()]);

            //tekst oproepen voor wedstrijd
            String tekst = mailService.mailTekst(houseId);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("teammanager.ucll@gmail.com");
            simpleMailMessage.setSubject("House created");
            simpleMailMessage.setTo(array);
            simpleMailMessage.setText(tekst);

            javaMailSender.send(simpleMailMessage);

            return new ResponseEntity<>("Mail sent successfully!", HttpStatus.OK);
        } catch (MailException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}