package com.example.messaging_back.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "participants")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt = LocalDateTime.now();

    //getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    public Conversation getConversation() {return conversation;}
    public void setConversation(Conversation conversation) {this.conversation = conversation;}

    public LocalDateTime getJoinedAt() {return joinedAt;}
    public void setJoinedAt(LocalDateTime joinedAt) {this.joinedAt = joinedAt;}
}
