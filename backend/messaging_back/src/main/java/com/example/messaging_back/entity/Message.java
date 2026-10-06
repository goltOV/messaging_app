package com.example.messaging_back.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(nullable = false)
    private String content;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt = LocalDateTime.now();

    //getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getSender() {return sender;}
    public void setSender(User sender) {this.sender = sender;}

    public Conversation getConversation() {return conversation;}
    public void setConversation(Conversation conversation) {this.conversation = conversation;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public LocalDateTime getSentAt() {return sentAt;}
    public void setSentAt(LocalDateTime sentAt) {this.sentAt = sentAt;}

}
