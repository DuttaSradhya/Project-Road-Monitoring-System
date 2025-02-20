package com.example.notification_service.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.example.notification_service.config.AuthorityClient;
import com.example.notification_service.dto.MailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final Configuration config;
    private final AuthorityClient authorityClient;

    public MailResponse sendMail(Map<String, Object> model){ //sendMail(MailRequest request, Map<String, Object> model)

        MailResponse response = new MailResponse();
        MimeMessage message = mailSender.createMimeMessage();


        try{
            List<String> emails = authorityClient.getMail();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                                            MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                                            StandardCharsets.UTF_8.name());

            Template template = config.getTemplate("notification-mail.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            String[] recipients = emails.toArray(new String[0]);
            log.info(recipients.toString());

            helper.setTo(recipients); //helper.setTo(request.getTo())
            helper.setText(html, true); 
            helper.setSubject("Test notification"); //helper.setSubject(request.getSubject())
            helper.setFrom("amily.maria02@gmail.com"); //helper.setFrom(request.getFrom());
            mailSender.send(message);

            response.setMessage("Notification has sent succesfully");
            response.setStatus(Boolean.TRUE);
        } catch(MessagingException  | IOException  | TemplateException exception){
            response.setMessage("Sent failed");
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

}
