package com.ssafy.hallhole.chat.service;

import com.ssafy.hallhole.chat.domain.Chatroom;
import com.ssafy.hallhole.performance.domain.Performance;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChatroomService {
    //채팅방 불러오기
    List<Chatroom> findAllRoom();

    //채팅방 하나 불러오기
    Chatroom findById(String roomId);

    //채팅방 생성
    void createRoom(Performance performance);

    void update(Chatroom chatroom);

    @Transactional
    void deleteRoom(String id);
}
