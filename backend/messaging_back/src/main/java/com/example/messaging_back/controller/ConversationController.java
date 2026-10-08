package com.example.messaging_back.controller;


import com.example.messaging_back.entity.Conversation;
import com.example.messaging_back.service.ConversationService;
import com.example.messaging_back.dto.CreateConversationRequest;
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
    public ResponseEntity<Conversation> createConversation(@RequestBody CreateConversationRequest request){
        return ResponseEntity.ok(
                conversationService.createConversation(
                        request.name(),
                        request.isGroup(),
                        request.membersIds()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conversation> getConversationById(@PathVariable UUID id){
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
    public ResponseEntity<Void> deleteConversation(@PathVariable UUID id){
        conversationService.deleteConversationById(id);
        return ResponseEntity.noContent().build();
    }
}
