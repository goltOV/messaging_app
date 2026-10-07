package com.example.messaging_back.dto;

import java.util.List;
import java.util.UUID;

public record CreateConversationRequest(String name, boolean isGroup, List<UUID> membersIds) {}
