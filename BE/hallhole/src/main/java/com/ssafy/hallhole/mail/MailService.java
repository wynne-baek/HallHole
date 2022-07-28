package com.ssafy.hallhole.mail;

import com.ssafy.hallhole.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailService{

    private JavaMailSender emailSender;

    public void sendMail(Member member) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("leemyo5435@gmail.com");

        message.setTo("leemyo_@naver.com");
        message.setSubject("비밀번호 변경 메일입니다.");

        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }

        message.setText(str);
        emailSender.send(message);
    }
}