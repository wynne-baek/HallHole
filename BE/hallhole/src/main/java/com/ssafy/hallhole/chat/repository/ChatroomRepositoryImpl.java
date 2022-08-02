package com.ssafy.hallhole.chat.repository;

import com.ssafy.hallhole.chat.domain.Chatroom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatroomRepositoryImpl implements ChatroomRepository{

    private final EntityManager em;

    @Override
    public List<Chatroom> findAllRooms() {
        return em.createQuery("select c from Chatroom c",Chatroom.class).getResultList();
    }

    @Override
    public Chatroom findRoomById(String roomId) {
        return em.createQuery("select r from Chatroom r where r.performance.id = :id",Chatroom.class).setParameter("id",roomId).getSingleResult();
    }

    @Override
    public void save(Chatroom chatroom) {
        em.persist(chatroom);
    }

    @Override
    public void update(Chatroom chatroom){
        em.merge(chatroom);
    }
}
