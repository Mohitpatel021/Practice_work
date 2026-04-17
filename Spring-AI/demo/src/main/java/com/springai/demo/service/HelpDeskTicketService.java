package com.springai.demo.service;

import com.springai.demo.entity.HelpDeskTool;
import com.springai.demo.repository.HelpDeskTicketRepository;
import com.springai.demo.request.HelpDeskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {
    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public String createTicket(HelpDeskRequest request,String username) {
        HelpDeskTool helpDeskTool= HelpDeskTool.builder()
                .username(username)
                .ticketNumber(UUID.randomUUID().toString())
                .issue(request.issue())
                .status("OPEN")
                .createdTime(LocalDateTime.now())
                .eta(LocalDateTime.now().plusDays(7))
                .build();
        return helpDeskTicketRepository.save(helpDeskTool).getTicketNumber();
    }

    public List<HelpDeskTool> getAllTickets(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}
