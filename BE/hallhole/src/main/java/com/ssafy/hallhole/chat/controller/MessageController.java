package com.ssafy.hallhole.chat.controller;


import com.ssafy.hallhole.chat.domain.ChatLog;
import com.ssafy.hallhole.chat.domain.ChatType;
import com.ssafy.hallhole.chat.domain.Chatroom;
import com.ssafy.hallhole.chat.repository.ChatroomRepository;
import com.ssafy.hallhole.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations sendingOperations;

    private final ChatService chatService;

    @MessageMapping("/chat/message")
    public void enter(ChatLog message) {
        System.out.println(message.getType());
        if (ChatType.ENTER.equals(message.getType())) {
            message.setMessage(message.getMemberId() + "님이 입장하였습니다.");
            // 사용자 추가
            Chatroom chatroom = chatService.findById(message.getPerformanceId());
            chatroom.addUser(message.getMemberId().toString());
            chatService.update(chatroom);
            System.out.println(chatroom.getMemberCnt());

        } else if (ChatType.TALK.equals(message.getType())) {
            //db 저장
            System.out.println("TALK "+message);

        } else if (ChatType.OUT.equals(message.getType())) {
            message.setMessage(message.getMemberId() + "님이 퇴장하였습니다.");
            // 사용자 제거
            Chatroom chatroom = chatService.findById(message.getPerformanceId());
            chatroom.subUser(message.getMemberId().toString());
            chatService.update(chatroom);
        }
        sendingOperations.convertAndSend("/topic/chat/room/" + message.getPerformanceId(), message);
    }
}