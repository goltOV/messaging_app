package com.example.messaging_back.service;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Participant;
import com.example.messaging_back.entity.User;
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
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final ParticipantRepository participantRepository;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, ParticipantRepository participantRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.participantRepository = participantRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Transactional
    public Conversation createConversation(String name, boolean isGroup, List<UUID> memberIds) {
        memberIds = memberIds.stream().distinct().toList();
        Conversation conversation = new Conversation();
        conversation.setName(name);
        conversation.setGroup(isGroup);
        Conversation saved = conversationRepository.save(conversation);

        saveParticipants(saved, memberIds);

        return saved;
    }

    private void saveParticipants(Conversation conversation, List<UUID> membersIds){
        List<User> users = userRepository.findAllById(membersIds);
        if (users.size() != membersIds.size())
            throw new RuntimeException("One or more users not found");
        List<Participant> participants = users.stream().map(user -> {
            Participant participant = new Participant();
            participant.setConversation(conversation);
            participant.setUser(user);
            return participant;
        }).toList();
        participantRepository.saveAll(participants);
    }

    @Transactional
    public void addParticipants(UUID conversationId, List<UUID> membersIds){
        membersIds = membersIds.stream().distinct().toList();
        Conversation conversation =
                conversationRepository.findById(conversationId)
                        .orElseThrow(() -> new RuntimeException("Conversation not found"));
        saveParticipants(conversation, membersIds);
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
