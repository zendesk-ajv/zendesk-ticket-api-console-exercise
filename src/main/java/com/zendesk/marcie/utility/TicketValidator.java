package com.zendesk.marcie.utility;

import org.springframework.stereotype.Component;

import com.zendesk.marcie.dto.DataContent;

import jakarta.xml.bind.ValidationException;

@Component
public class TicketValidator {

    public void validateUserInput(Integer ticketId) throws ValidationException {
        if (null == ticketId) {
            throw new ValidationException("Validation failed Ticket id should not be null");
        }
    }

    public void validateDataContent(DataContent dataContent) throws ValidationException {
        if (null == dataContent) {
            throw new ValidationException("Validation failed request body should not be empty");
        }
    }

    public void validatePageNumberAndSize(int pageNumber, int pageSize) {
    }

}
