package com.example.messaging_back.repository;

import com.example.messaging_back.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {


}
