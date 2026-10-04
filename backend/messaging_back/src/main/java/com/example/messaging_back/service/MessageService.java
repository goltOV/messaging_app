package com.example.messaging_back.service;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Message;
import com.example.messaging_back.entity.User;
import com.example.messaging_back.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public Message createMessage(User sender, Conversation conversation, String content){
        Message message = new Message();
        message.setSender(sender);
        message.setConversation(conversation);
        message.setContent(content);
        return messageRepository.save(message);
    }

    public Optional<Message> getMessageById(UUID id){
        return messageRepository.findById(id);
    }

    public List<Message> getMessagesByConversation(Conversation conversation){
        return messageRepository.findByConversationOrderBySentAtAsc(conversation);
    }

    public void deleteMessage(UUID id){
        messageRepository.deleteById(id);
    }
}
