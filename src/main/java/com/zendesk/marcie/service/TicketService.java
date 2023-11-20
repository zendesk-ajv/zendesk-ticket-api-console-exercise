package com.zendesk.marcie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zendesk.marcie.dto.DataContent;
import com.zendesk.marcie.dto.Ticket;
import com.zendesk.marcie.integration.ZendeskSupportApiService;
import com.zendesk.marcie.utility.TicketValidator;

import jakarta.xml.bind.ValidationException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TicketService {

    @Autowired
    private ZendeskSupportApiService zendeskSupportApiService;

    @Autowired
    private TicketValidator ticketValidator;

    public Flux<DataContent> getAllTicket() {
        return zendeskSupportApiService.getAllTicket();
    }

    public Mono<Ticket> getTicket(Integer ticketId) throws ValidationException {
        ticketValidator.validateUserInput(ticketId);
        return zendeskSupportApiService.getTicket(ticketId);
    }

    public Mono<Ticket> updateTicket(Integer ticketId, DataContent dataContent) throws ValidationException {
        ticketValidator.validateUserInput(ticketId);
        ticketValidator.validateDataContent(dataContent);
        return zendeskSupportApiService.updateTicket(ticketId, dataContent);
    }

    public Mono<Object> deleteTicket(Integer ticketId) throws ValidationException {
        ticketValidator.validateUserInput(ticketId);
        return zendeskSupportApiService.deleteTicket(ticketId);
    }

    public List<Ticket> findPaginatedTicket(int pageNumber, int pageSize) {
        
        ticketValidator.validatePageNumberAndSize(pageNumber, pageSize);

        List<Ticket> listOfTickets = getAllTicket().map(DataContent::getTickets).blockFirst();
        
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        // Calculate start and end item indexes
        System.out.println("size of ticket list " + listOfTickets.size());
        int startItem = (int) pageable.getOffset();
        int endItem = Math.min(startItem + pageSize, listOfTickets.size());

        // Get sublist and create PageImpl
        List<Ticket> pageList = listOfTickets.subList(startItem, endItem);
        Page<Ticket> dataContentPage = new PageImpl<>(pageList, pageable, listOfTickets.size());
        return dataContentPage.getContent();
    }
}
