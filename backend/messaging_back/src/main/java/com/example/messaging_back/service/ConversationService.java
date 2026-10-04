package com.example.messaging_back.service;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Participant;
import com.example.messaging_back.entity.User;
import com.example.messaging_back.repository.ConversationRepository;
import com.example.messaging_back.repository.ParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final ParticipantRepository participantRepository;

    public ConversationService(ConversationRepository conversationRepository, ParticipantRepository participantRepository) {
        this.conversationRepository = conversationRepository;
        this.participantRepository = participantRepository;
    }

    public Conversation createConversation(String name, boolean isGroup, List<User> members) {
        Conversation conversation = new Conversation();
        conversation.setName(name);
        conversation.setGroup(isGroup);
        Conversation saved = conversationRepository.save(conversation);
        for (User user: members){
            Participant participant = new Participant();
            participant.setUser(user);
            participant.setConversation(saved);
            participantRepository.save(participant);
        }
        return saved;
    }

    public Optional<Conversation> getConversationById(UUID id) {
        return conversationRepository.findById(id);
    }

    public List<Conversation> getAllConversations() {
        return conversationRepository.findAll();
    }

    public void deleteConversation(UUID id) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        participantRepository.deleteByConversation(conversation);
        conversationRepository.deleteById(id);

    }
}
