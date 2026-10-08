package com.example.messaging_back.controller;

import com.example.messaging_back.dto.CreateMessageRequest;
import com.example.messaging_back.entity.Message;
import com.example.messaging_back.service.ConversationService;
import com.example.messaging_back.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;
    private final ConversationService conversationService;

    public MessageController(MessageService messageService, ConversationService conversationService){
        this.messageService = messageService;
        this.conversationService = conversationService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody CreateMessageRequest request){
        return ResponseEntity.ok(
                messageService.createMessage(
                        request.senderId(),
                        request.conversationId(),
                        request.content()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(@RequestParam UUID id){
        return messageService.getMessageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Message>> getMessagesByConversation(@RequestParam Conversation request){
        return ResponseEntity.ok(
                messageService.getMessagesByConversation(request)
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMessage(@RequestParam UUID id){
        messageService.deleteMessage(id);
        return ResponseEntity.noContent().build();
    }
}
