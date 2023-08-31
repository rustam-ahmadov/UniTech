package com.rustam.unitech.entity;


import com.rustam.unitech.dto.request.account.AccountCreationRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number", length = 16, nullable = false)
    private String number;
    @Column(name = "pin")
    private int pin;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "is_active")
    private boolean isActive;
    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
