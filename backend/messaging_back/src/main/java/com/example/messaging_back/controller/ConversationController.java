package com.example.messaging_back.controller;


import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.entity.User;
import com.example.messaging_back.service.ConversationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService){
        this.conversationService = conversationService;
    }

    @PostMapping
    public ResponseEntity<Conversation> createConversation(@RequestBody Conversation request, List<User> members){
        return ResponseEntity.ok(
                conversationService.createConversation(
                        request.getName(),
                        request.isGroup(),
                        members
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversationById(@RequestParam UUID id){
        return conversationService.getConversationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Conversation>> getAllConversations(){
        return ResponseEntity.ok(
                conversationService.getAllConversations()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversation(@RequestParam UUID id){
        conversationService.deleteConversation(id);
        return ResponseEntity.noContent().build();
    }
}
