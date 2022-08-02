package com.ssafy.hallhole.chat.service;

import com.ssafy.hallhole.chat.domain.Chatroom;
import com.ssafy.hallhole.chat.repository.ChatLogRepository;
import com.ssafy.hallhole.chat.repository.ChatroomRepository;
import com.ssafy.hallhole.performance.domain.Performance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatroomRepository chatroomRepository;

    //채팅방 불러오기
    @Override
    public List<Chatroom> findAllRoom() {
        return chatroomRepository.findAllRooms();
    }

    //채팅방 하나 불러오기
    @Override
    public Chatroom findById(String roomId) {
        return chatroomRepository.findRoomById(roomId);
    }

    //채팅방 생성
    @Override
    @Transactional
    public void createRoom(Performance performance) {
        Chatroom chatroom = Chatroom.builder()
                .performance(performance)
                .name(performance.getName())
                .openTime(performance.getStartDate())
                .closeTime(performance.getEndDate())
                .memberNameList(new ArrayList<>())
                .build();
        chatroomRepository.save(chatroom);
    }

    @Override
    @Transactional
    public void update(Chatroom chatroom) {
        chatroomRepository.update(chatroom);
    }

}
