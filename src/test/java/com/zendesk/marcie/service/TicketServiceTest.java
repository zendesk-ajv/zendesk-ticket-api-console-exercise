package com.zendesk.marcie.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.zendesk.marcie.dto.DataContent;
import com.zendesk.marcie.dto.Ticket;
import com.zendesk.marcie.integration.ZendeskSupportApiService;
import com.zendesk.marcie.utility.TicketValidator;

import jakarta.xml.bind.ValidationException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    ZendeskSupportApiService zendeskSupportApiService;

    @Mock
    TicketValidator ticketValidator;

    @InjectMocks
    TicketService ticketService;

    @Test
    void getAllTicket() {
        DataContent dataContent = new DataContent(); // set the required fields
        Mockito.when(zendeskSupportApiService.getAllTicket()).thenReturn(Flux.just(dataContent));
        
        StepVerifier.create(ticketService.getAllTicket())
            .expectNext(dataContent)
            .verifyComplete();
    }

    @Test
    void getTicket() throws ValidationException {
        int ticketId = 123;
        Ticket dataContent = new Ticket(); // set the required fields
        Mockito.when(zendeskSupportApiService.getTicket(ticketId)).thenReturn(Mono.just(dataContent));
        
        ticketService.getTicket(ticketId);
        
        Mockito.verify(ticketValidator).validateUserInput(ticketId);
    }

    @Test
    void updateTicket() throws ValidationException {
        int ticketId = 123;
        DataContent dataContent = new DataContent(); // set the required fields
        Ticket ticket = new Ticket();
        Mockito.when(zendeskSupportApiService.updateTicket(ticketId, dataContent)).thenReturn(Mono.just(ticket));
        
        ticketService.updateTicket(ticketId, dataContent);
        
        Mockito.verify(ticketValidator).validateUserInput(ticketId);
        Mockito.verify(ticketValidator).validateDataContent(dataContent);
    }

    @Test
    void deleteTicket() throws ValidationException {
        int ticketId = 123;
        Mockito.when(zendeskSupportApiService.deleteTicket(ticketId)).thenReturn(Mono.empty());
        
        ticketService.deleteTicket(ticketId);
        
        Mockito.verify(ticketValidator).validateUserInput(ticketId);
    }

    @Test
    void findPaginatedTicket() {
        // Init
        int pageNumber = 1;
        int pageSize = 4;
        List<Ticket> ticketList = new ArrayList<>(); // populate this list as needed

        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());
        ticketList.add(new Ticket());


        // Mock/WebClient call
        DataContent dataContent = new DataContent();
        dataContent.setTickets(ticketList);
        Mockito.when(zendeskSupportApiService.getAllTicket()).thenReturn(Flux.just(dataContent));

        // Test
        List<Ticket> ticketPage = ticketService.findPaginatedTicket(pageNumber, pageSize);

        // Validate
        assertEquals(pageSize, ticketPage.size());
    }
}
