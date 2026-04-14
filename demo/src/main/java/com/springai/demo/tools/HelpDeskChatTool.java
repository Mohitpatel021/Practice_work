package com.springai.demo.tools;

import com.springai.demo.entity.HelpDeskTool;
import com.springai.demo.request.HelpDeskRequest;
import com.springai.demo.service.HelpDeskTicketService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpDeskChatTool {


    public static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HelpDeskChatTool.class);
    private final HelpDeskTicketService helpDeskTicketService;


    @Tool(name = "createTicket", description = "Create the Support Ticket", returnDirect = true)
    String createTicket(@ToolParam(description = "Details to create a Support ticket")
                        HelpDeskRequest ticketRequest, ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Creating support ticket for user: {} with details: {}", username, ticketRequest);
        String ticketId = helpDeskTicketService.createTicket(ticketRequest,username);
        LOGGER.info("Ticket created successfully. Ticket ID: {}:", ticketId );
        return "Ticket #" + ticketId + " created successfully for user ";
    }

    @Tool(description = "Fetch the status of the tickets based on a given username")
    List<HelpDeskTool> getTicketStatus(ToolContext toolContext) {
        String username = (String) toolContext.getContext().get("username");
        LOGGER.info("Fetching tickets for user: {}", username);
        List<HelpDeskTool> tickets =  helpDeskTicketService.getAllTickets(username);
        LOGGER.info("Found {} tickets for user: {}", tickets.size(), username);
        return tickets;
    }
}
