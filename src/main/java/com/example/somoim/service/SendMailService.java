package com.example.somoim.service;

import com.example.somoim.error.ServiceError;
import com.example.somoim.error.ServiceException;
import com.example.somoim.model.admin.AdminUser;
import com.example.somoim.model.member.PasswordResetToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendMailService {

    private final JavaMailSender javaMailSender;

    @Value(value = "${my.app.domain.address}")
    private String myAppAddress;

    public void sendPasswordResetMail(PasswordResetToken passwordResetToken){
        String url = myAppAddress + "/user/changePassword?id=" +
                passwordResetToken.getAdminUser().getAdminId() + "&token=" + passwordResetToken.getToken();
        String message = "message.resetPassword";

        sendMail("Reset Password", message + " \r\n" + url, passwordResetToken.getAdminUser());
    }

    public void sendMail(String subject, String body, AdminUser adminUser)throws ServiceException {
       try {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setSubject(subject);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(adminUser.getEmail()));
        message.setText(body);
        message.setSentDate(new Date());
        javaMailSender.send(message);
       } catch (AddressException e){
           log.error("sned Mail error : ",e);
           throw new ServiceException(ServiceError.SEND_MAIL_ERROR);
       } catch (MessagingException ex) {
           log.error("sned Mail error2 : ",ex);
           throw new ServiceException(ServiceError.SEND_MAIL_ERROR);
       }catch (Exception xx){
           log.error("sned Mail error3 : ",xx);
           throw new ServiceException(ServiceError.SEND_MAIL_ERROR);
       }

    }
}
