package com.example.messaging_back.repository;

import com.example.messaging_back.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    @Query("SELECT p.conversation FROM Participant p WHERE p.user.id = :userId")
    List<Conversation> findByUserId(@Param("userId") UUID userId);

}
