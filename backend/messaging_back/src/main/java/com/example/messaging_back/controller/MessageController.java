package com.example.messaging_back.controller;

import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.Message;
import com.example.messaging_back.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message request){
        return ResponseEntity.ok(
                messageService.createMessage(
                        request.getSender(),
                        request.getConversation(),
                        request.getContent()
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
