package com.zendesk.marcie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ticket {

    @JsonProperty("subject")
    private String subject;
    private Integer id;
    private String created_at;
    private String priority;
    private String status;
    
}
