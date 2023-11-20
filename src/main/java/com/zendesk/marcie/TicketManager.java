package com.zendesk.marcie;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.zendesk.marcie.dto.DataContent;
import com.zendesk.marcie.dto.Ticket;
import com.zendesk.marcie.service.TicketService;

import jakarta.xml.bind.ValidationException;

@Component
public class TicketManager {

    private Scanner scanner;

    @Autowired
    private TicketService ticketService;

    private List<Ticket> ticketList;

    public TicketManager() {
        this.ticketList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            System.out.print("Enter Option : ");
            int choice = scanner.nextInt();
            handleChoice(choice);
        }
    }

    private void showMenu() {
        String leftAlignFormat = "| %-10s | %-30s |%n";

        System.out.format("+------------+--------------------------------+%n");
        System.out.format("| Option     | Description                    |%n");
        System.out.format("+------------+--------------------------------+%n");
        System.out.format(leftAlignFormat, "1", "Show All Ticket");
        System.out.format(leftAlignFormat, "2", "Show Ticket");
        System.out.format(leftAlignFormat, "3", "Update Ticket Subject");
        System.out.format(leftAlignFormat, "4", "Delete Ticket");
        System.out.format(leftAlignFormat, "5", "Exit");
        System.out.format("+------------+--------------------------------+%n");
        ;
    }

    private void handleChoice(int choice) {

        switch (choice) {
            case 1:
                String leftAlignFormat = getLeftAlignFormat();
                try {
                    ticketList = ticketService.getAllTicket().collectList().block().get(0).getTickets();
                    ticketList.stream().forEach(ticket -> {
                        System.out.format(leftAlignFormat, ticket.getId(), ticket.getCreated_at(), ticket.getPriority(),
                                ticket.getStatus(), ticket.getSubject());
                    });
                } catch (Exception e) {
                    System.out.println("Something went wrong..");
                }
                System.out.println("\n\n\n");
                // showMenu();

                break;

            case 2:

                System.out.print("\t Enter Ticket Number : ");
                int ticketNumber = scanner.nextInt();
                try {
                    Ticket ticket = ticketService.getTicket(ticketNumber).block();
                    System.out.format(getLeftAlignFormat(), ticket.getId(),
                            ticket.getCreated_at(), ticket.getPriority(),
                            ticket.getStatus(), ticket.getSubject());
                    System.out.println();
                } catch (ValidationException e) {
                    System.out.println("Validation failed.....");
                } catch (Exception e) {
                    System.out.println("Something went wrong .. ");
                }

                // showMenu();
                break;
            case 3:
                System.out.print("\t Enter Ticket Number : ");
                ticketNumber = scanner.nextInt();
                System.out.println();
                System.out.print("\t Enter Ticket Subject : ");
                String ticketSubject = scanner.next();
                
                try {
                    DataContent dataContent = new DataContent();
                    Ticket newTicket = new Ticket();
                    newTicket.setSubject(ticketSubject);
                    dataContent.setTicket(newTicket);
                    Ticket ticket = ticketService.updateTicket(ticketNumber, dataContent).block();

                    System.out.format(getLeftAlignFormat(), ticket.getId(),
                            ticket.getCreated_at(), ticket.getPriority(),
                            ticket.getStatus(), ticket.getSubject());
                    System.out.println();
                } catch (ValidationException e) {
                    System.out.println("Validation failed.....");
                } catch (Exception e) {
                    System.out.println("Something went wrong .. ");
                }

                break;
            case 4:
                System.out.println("\t Enter Ticket Number : ");
                ticketNumber = scanner.nextInt();
                try {

                    ticketService.deleteTicket(ticketNumber);
                    System.out.println("Ticket deleted successfully..");
                } catch (ValidationException e) {
                    System.out.println("Validation failed.....");
                } catch (Exception e) {
                    System.out.println("Something went wrong .. ");
                }

                break;
            case 5:

                System.out.println("\nClosing application...");
                System.exit(0);

                break;

            default:
                break;
        }

        // ticketService.getAllTicket();

    }

    private String getLeftAlignFormat() {
        String leftAlignFormat = "| %-10s | %-30s | %-10s | %-10s | %-60s |%n";
        System.out.println("\n\n\n");
        System.out.format(
                "+------------+--------------------------------+------------+------------+--------------------------------------------------------------+%n");
        System.out.format(
                "| ID         | Created at                    |  priority   | status     |   Subject                                                    |%n");
        System.out.format(
                "+------------+--------------------------------+------------+------------+--------------------------------------------------------------+%n");
        // System.out.format(leftAlignFormat, "1", "Show All Ticket","","","");
        return leftAlignFormat;
    }

}
