package com.main.sts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "faq")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "faq")
public class FAQ {

    @Id
    // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
    // "faq_seq_gen")
    // @SequenceGenerator(name = "faq_seq_gen", sequenceName = "faq_id_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "faq_seq_gen")
    @SequenceGenerator(name = "faq_seq_gen", sequenceName = "faq_id_seq")
    private int id;
    private String question;
    private String answer;
    private Integer priority;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}
