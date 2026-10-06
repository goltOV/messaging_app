package com.example.messaging_back.repository;

import com.example.messaging_back.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface ParticipantRepository extends JpaRepository<Participant, UUID> {

    List<Participant> findByUserId(UUID userId);

    List<Participant> findByConversationId(UUID conversationId);

    @Transactional
    void deleteByConversationId(UUID conversationId);
}
