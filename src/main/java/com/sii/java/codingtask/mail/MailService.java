package com.sii.java.codingtask.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailService {

    private Long mailIdGenerator = 1L;

    public void sendMail(final Mail mail) {
        sendAsyncMail(mail);
    }

    @Async
    void sendAsyncMail(Mail mail) {
        log.debug("Mail with ID: " + mailIdGenerator + " " + mail.toString());
        mailIdGenerator = mailIdGenerator + 1;
    }
}
