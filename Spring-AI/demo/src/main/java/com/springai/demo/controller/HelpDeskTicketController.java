package com.springai.demo.controller;

import com.springai.demo.entity.HelpDeskTool;
import com.springai.demo.repository.HelpDeskTicketRepository;
import com.springai.demo.request.HelpDeskRequest;
import com.springai.demo.service.HelpDeskTicketService;
import com.springai.demo.tools.HelpDeskChatTool;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@RestController
@RequestMapping("/api/helpdesk")
public class HelpDeskTicketController {

    private final HelpDeskTicketService helpDeskTicketService;

    private final HelpDeskChatTool helpDeskTool;

    private final ChatClient chatClient;

        public HelpDeskTicketController(HelpDeskTicketService helpDeskTicketService, @Qualifier("helpDeskChatClient")ChatClient chatClient, HelpDeskChatTool helpDeskChatTool) {
            this.helpDeskTicketService = helpDeskTicketService;
            this.chatClient = chatClient;
            this.helpDeskTool = helpDeskChatTool;
        }

        @GetMapping("/chat")
        public ResponseEntity<?> createTicketUsingChatTool(@RequestHeader("username")String username, @RequestParam("message")String message){
            return ResponseEntity.ok(chatClient.prompt()
                    .tools(helpDeskTool)
                    .advisors(ad->ad.param(CONVERSATION_ID,username))
                    .user(message)
                    .toolContext(Map.of("username",username))
                    .call().content());
        }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestHeader(name ="username") String username, @RequestBody HelpDeskRequest request) {
        return ResponseEntity.ok(helpDeskTicketService.createTicket(request,username));
    }

    @GetMapping("/get")
    public  ResponseEntity<?> getTicket(@RequestHeader( name="username") String username) {
        return ResponseEntity.ok(helpDeskTicketService.getAllTickets(username));
    }

}