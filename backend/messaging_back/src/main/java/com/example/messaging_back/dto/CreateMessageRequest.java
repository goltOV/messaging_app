package com.example.messaging_back.dto;

import java.util.UUID;

public record CreateMessageRequest(UUID senderId, UUID conversationId, String content) {
}
