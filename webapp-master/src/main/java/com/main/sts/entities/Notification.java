package com.main.sts.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "notifications_seq_gen")
    @SequenceGenerator(name = "notifications_seq_gen", sequenceName = "notifications_id_seq")
    private int id;
    private int commuter_id;
    private String title;
    private String message;
    // from google's response.
    private String message_id;
    private Date created_at;
    private Date updated_at;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCommuter_id() {
        return commuter_id;
    }
    public void setCommuter_id(int commuter_id) {
        this.commuter_id = commuter_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public String getMessage_id() {
        return message_id;
    }
    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

}
