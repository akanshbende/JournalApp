package com.example.JournalApp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendEmail (String toEmail,String subject,String body){
        try {

            SimpleMailMessage mail=new SimpleMailMessage();
            mail.setTo(toEmail);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
            log.info("Email Sent Successfully to : {}!!!!",toEmail);
            return true;

        } catch (Exception e) {
            log.error("Error occurred while sending email :",e);
            return false;
        }
    }

}
