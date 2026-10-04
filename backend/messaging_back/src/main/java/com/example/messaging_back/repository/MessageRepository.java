package com.example.messaging_back.repository;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {

    List<Message> findByConversationOrderBySentAtAsc(Conversation conversation);

}
