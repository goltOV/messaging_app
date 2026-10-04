package com.example.messaging_back.repository;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Participant;
import com.example.messaging_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    List<Participant> findByUser(User user);

    List<Participant> findByConversation(Conversation conversation);

    @Transactional
    void deleteByConversation(Conversation conversation);
}
