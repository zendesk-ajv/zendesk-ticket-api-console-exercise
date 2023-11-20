package com.zendesk.marcie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DataContent {
 private Ticket TicketObject;

 public List<Ticket> tickets;

 // Getter Methods 

 public List<Ticket> getTickets() {
    return tickets;
}

public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
}

public Ticket getTicket() {
  return TicketObject;
 }

 // Setter Methods 

 public void setTicket(Ticket ticketObject) {
  this.TicketObject = ticketObject;
 }
}

/* 
@JsonInclude(JsonInclude.Include.NON_NULL)
 class Ticket1 {
 private String url;
 private float id;
 private String external_id = null;
 private String created_at;
 private String updated_at;
 private String type;
 private String subject;
 private String raw_subject;
 private String description;
 private String priority;
 private String status;
 private String recipient = null;
 private float requester_id;
 private float submitter_id;
 private float assignee_id;
 private String organization_id = null;
 private float group_id;
 
 private String forum_topic_id = null;
 private String problem_id = null;
 private boolean has_incidents;
 private boolean is_public;
 private String due_at = null;

 private float ticket_form_id;
 private float brand_id;
 private boolean allow_channelback;
 private boolean allow_attachments;
 private boolean from_messaging_channel;


 // Getter Methods 

 public String getUrl() {
  return url;
 }

 public float getId() {
  return id;
 }

 public String getExternal_id() {
  return external_id;
 }


 public String getCreated_at() {
  return created_at;
 }

 public String getUpdated_at() {
  return updated_at;
 }

 public String getType() {
  return type;
 }

 public String getSubject() {
  return subject;
 }

 public String getRaw_subject() {
  return raw_subject;
 }

 public String getDescription() {
  return description;
 }

 public String getPriority() {
  return priority;
 }

 public String getStatus() {
  return status;
 }

 public String getRecipient() {
  return recipient;
 }

 public float getRequester_id() {
  return requester_id;
 }

 public float getSubmitter_id() {
  return submitter_id;
 }

 public float getAssignee_id() {
  return assignee_id;
 }

 public String getOrganization_id() {
  return organization_id;
 }

 public float getGroup_id() {
  return group_id;
 }

 public String getForum_topic_id() {
  return forum_topic_id;
 }

 public String getProblem_id() {
  return problem_id;
 }

 public boolean getHas_incidents() {
  return has_incidents;
 }

 public boolean getIs_public() {
  return is_public;
 }

 public String getDue_at() {
  return due_at;
 }


 public float getTicket_form_id() {
  return ticket_form_id;
 }

 public float getBrand_id() {
  return brand_id;
 }

 public boolean getAllow_channelback() {
  return allow_channelback;
 }

 public boolean getAllow_attachments() {
  return allow_attachments;
 }

 public boolean getFrom_messaging_channel() {
  return from_messaging_channel;
 }

 // Setter Methods 

 public void setUrl(String url) {
  this.url = url;
 }

 public void setId(float id) {
  this.id = id;
 }

 public void setExternal_id(String external_id) {
  this.external_id = external_id;
 }


 public void setCreated_at(String created_at) {
  this.created_at = created_at;
 }

 public void setUpdated_at(String updated_at) {
  this.updated_at = updated_at;
 }

 public void setType(String type) {
  this.type = type;
 }

 public void setSubject(String subject) {
  this.subject = subject;
 }

 public void setRaw_subject(String raw_subject) {
  this.raw_subject = raw_subject;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public void setPriority(String priority) {
  this.priority = priority;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public void setRecipient(String recipient) {
  this.recipient = recipient;
 }

 public void setRequester_id(float requester_id) {
  this.requester_id = requester_id;
 }

 public void setSubmitter_id(float submitter_id) {
  this.submitter_id = submitter_id;
 }

 public void setAssignee_id(float assignee_id) {
  this.assignee_id = assignee_id;
 }

 public void setOrganization_id(String organization_id) {
  this.organization_id = organization_id;
 }

 public void setGroup_id(float group_id) {
  this.group_id = group_id;
 }

 public void setForum_topic_id(String forum_topic_id) {
  this.forum_topic_id = forum_topic_id;
 }

 public void setProblem_id(String problem_id) {
  this.problem_id = problem_id;
 }

 public void setHas_incidents(boolean has_incidents) {
  this.has_incidents = has_incidents;
 }

 public void setIs_public(boolean is_public) {
  this.is_public = is_public;
 }

 public void setDue_at(String due_at) {
  this.due_at = due_at;
 }

 

 public void setTicket_form_id(float ticket_form_id) {
  this.ticket_form_id = ticket_form_id;
 }

 public void setBrand_id(float brand_id) {
  this.brand_id = brand_id;
 }

 public void setAllow_channelback(boolean allow_channelback) {
  this.allow_channelback = allow_channelback;
 }

 public void setAllow_attachments(boolean allow_attachments) {
  this.allow_attachments = allow_attachments;
 }

 public void setFrom_messaging_channel(boolean from_messaging_channel) {
  this.from_messaging_channel = from_messaging_channel;
 }
}*/
