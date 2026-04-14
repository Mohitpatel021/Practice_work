package com.springai.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name="helpdesk_ticket")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HelpDeskTool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ticketNumber;
    private String username;
    private String status;
    private String issue;
    private LocalDateTime createdTime;
    private LocalDateTime eta;
}
