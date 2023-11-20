package com.zendesk.marcie.integration;

import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.zendesk.marcie.dto.DataContent;
import com.zendesk.marcie.dto.Ticket;
import com.zendesk.marcie.exception.ApiUnavailableException;
import com.zendesk.marcie.service.TicketService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ZendeskSupportApiService {


        @Autowired
        @Lazy
        private TicketService ticketService;

        private final WebClient webClient;

        public ZendeskSupportApiService(WebClient webClient) {
                this.webClient = webClient;
        }

        public Flux<DataContent> getAllTicket() {
                Flux<DataContent> listOfTickets = (webClient.get()
                                .uri(uriBuilder -> uriBuilder.path("/api/v2/tickets/")
                                                .build())
                                .retrieve()
                                .onStatus(status -> status == HttpStatus.UNAUTHORIZED,
                                                response -> Mono.error(new AuthenticationException(
                                                                "Authentication failed Invlid credential")))
                                .onStatus(HttpStatusCode::is5xxServerError,
                                                response -> Mono.error(new ApiUnavailableException(
                                                                "Zendesk api not availables")))
                                .bodyToFlux(DataContent.class));


                return listOfTickets;
        }

       

        public Mono<Ticket> getTicket(Integer ticketId) {
                Mono<DataContent> ticketData = (webClient.get()
                                .uri(uriBuilder -> uriBuilder.path("/api/v2/tickets/" + ticketId)
                                                .build())
                                .retrieve()
                                .onStatus(status -> status == HttpStatus.UNAUTHORIZED,
                                                response -> Mono.error(new AuthenticationException(
                                                                "Authentication failed Invlid credential")))
                                .onStatus(HttpStatusCode::is5xxServerError,
                                                response -> Mono.error(new ApiUnavailableException(
                                                                "Zendesk api not availables")))
                                .bodyToMono(DataContent.class));

                return ticketData.map(DataContent::getTicket);

        }

        public Mono<Ticket> updateTicket(Integer ticketId, DataContent dataContent) {

                Mono<DataContent> updatedTicket = webClient.put()
                                .uri(uriBuilder -> uriBuilder.path("/api/v2/tickets/" + ticketId)
                                                .build())
                                .body(BodyInserters.fromValue(dataContent))
                                .retrieve()
                                .onStatus(status -> status == HttpStatus.UNAUTHORIZED,
                                                response -> Mono.error(new AuthenticationException(
                                                                "Authentication failed Invlid credential")))
                                .onStatus(HttpStatusCode::is5xxServerError,
                                                response -> Mono.error(new ApiUnavailableException(
                                                                "Zendesk api not availables")))
                                .bodyToMono(DataContent.class);
                return updatedTicket.map(DataContent::getTicket);
        }

        public Mono<Object> deleteTicket(Integer ticketId) {
                Mono<Object> deletedTicket = webClient.delete()
                                .uri(uriBuilder -> uriBuilder.path("/api/v2/tickets/" + ticketId)
                                                .build())
                                .retrieve()
                                .onStatus(status -> status == HttpStatus.UNAUTHORIZED,
                                                response -> Mono.error(new AuthenticationException(
                                                                "Authentication failed Invlid credential")))
                                .onStatus(HttpStatusCode::is5xxServerError,
                                                response -> Mono.error(new ApiUnavailableException(
                                                                "Zendesk api not availables")))
                                .bodyToMono(Object.class);
                return deletedTicket;
        }
}
