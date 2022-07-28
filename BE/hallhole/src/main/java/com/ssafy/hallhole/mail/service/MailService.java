package com.ssafy.hallhole.mail.service;

import com.ssafy.hallhole.mail.dto.MailDTO;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender emailSender;

    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        MailDTO mailDto = new MailDTO();
        message.setFrom("leemyo5435@gmail.com");

        mailDto.setAddress("wodnd101@naver.com");
        mailDto.setTitle("비밀번호 변경 메일입니다.");

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());



        mailDto.setContent(str);
        message.setText(mailDto.getContent());
        emailSender.send(message);
    }
}
