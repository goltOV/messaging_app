package com.example.messaging_back.service;

import com.example.messaging_back.entity.Message;
import com.example.messaging_back.repository.ConversationRepository;
import com.example.messaging_back.repository.MessageRepository;
import com.example.messaging_back.repository.ParticipantRepository;
import com.example.messaging_back.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ParticipantRepository participantRepository;

    public MessageService(ConversationRepository conversationRepository, UserRepository userRepository, MessageRepository messageRepository, ParticipantRepository participantRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.participantRepository = participantRepository;
    }

    @Transactional
    public Message createMessage(UUID senderId, UUID conversationId, String content){
        Message message = new Message();
        message.setSender(
                userRepository.findById(senderId)
                        .orElseThrow(() -> new RuntimeException("user not found"))
        );
        message.setConversation(
                conversationRepository.findById(conversationId)
                        .orElseThrow(() -> new RuntimeException("conversation not found"))
        );
        message.setContent(content);
        if (!participantRepository.existsByUserIdAndConversationId(senderId, conversationId))
            throw new RuntimeException("user is not in conversation");
        return messageRepository.save(message);
    }

    public Optional<Message> getMessageById(UUID id){
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByConversationId(UUID conversationId){
        return messageRepository.findByConversationIdOrderBySentAtAsc(conversationId);
    }

    @Transactional
    public void deleteMessage(UUID id){
        messageRepository.deleteById(id);
    }
}
