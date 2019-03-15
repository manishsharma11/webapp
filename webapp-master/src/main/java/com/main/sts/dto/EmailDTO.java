package com.main.sts.dto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.sendgrid.smtpapi.SMTPAPI;

public class EmailDTO {
    private SMTPAPI smtpapi;
    private ArrayList<String> to;
    private ArrayList<String> toname;
    private ArrayList<String> cc;
    private String from;
    private String fromname;
    private String replyto;
    private String subject;
    private String text;
    private String html;
    private ArrayList<String> bcc;
    private Map<String, InputStream> attachments;
    private Map<String, String> contents;
    private Map<String, String> headers;

    public EmailDTO() {
        this.smtpapi = new SMTPAPI();
        this.to = new ArrayList<String>();
        this.toname = new ArrayList<String>();
        this.cc = new ArrayList<String>();
        this.bcc = new ArrayList<String>();
        this.attachments = new HashMap<String, InputStream>();
        this.contents = new HashMap<String, String>();
        this.headers = new HashMap<String, String>();
    }

    public EmailDTO addTo(String to) {
        this.to.add(to);
        return this;
    }

    public EmailDTO addTo(String[] tos) {
        this.to.addAll(Arrays.asList(tos));
        return this;
    }

    public EmailDTO addTo(String to, String name) {
        this.addTo(to);
        return this.addToName(name);
    }

    public EmailDTO setTo(String[] tos) {
        this.to = new ArrayList<String>(Arrays.asList(tos));
        return this;
    }

    public String[] getTos() {
        return this.to.toArray(new String[this.to.size()]);
    }

    public EmailDTO addSmtpApiTo(String to) {
        this.smtpapi.addTo(to);
        return this;
    }

    public EmailDTO addSmtpApiTo(String[] to) {
        this.smtpapi.addTos(to);
        return this;
    }

    public EmailDTO addToName(String toname) {
        this.toname.add(toname);
        return this;
    }

    public EmailDTO addToName(String[] tonames) {
        this.toname.addAll(Arrays.asList(tonames));
        return this;
    }

    public EmailDTO setToName(String[] tonames) {
        this.toname = new ArrayList<String>(Arrays.asList(tonames));
        return this;
    }

    public String[] getToNames() {
        return this.toname.toArray(new String[this.toname.size()]);
    }

    public EmailDTO addCc(String cc) {
        this.cc.add(cc);
        return this;
    }

    public EmailDTO addCc(String[] ccs) {
        this.cc.addAll(Arrays.asList(ccs));
        return this;
    }

    public EmailDTO setCc(String[] ccs) {
        this.cc = new ArrayList<String>(Arrays.asList(ccs));
        return this;
    }

    public String[] getCcs() {
        return this.cc.toArray(new String[this.cc.size()]);
    }

    public EmailDTO setFrom(String from) {
        this.from = from;
        return this;
    }

    public String getFrom() {
        return this.from;
    }

    public EmailDTO setFromName(String fromname) {
        this.fromname = fromname;
        return this;
    }

    public String getFromName() {
        return this.fromname;
    }

    public EmailDTO setReplyTo(String replyto) {
        this.replyto = replyto;
        return this;
    }

    public String getReplyTo() {
        return this.replyto;
    }

    public EmailDTO addBcc(String bcc) {
        this.bcc.add(bcc);
        return this;
    }

    public EmailDTO addBcc(String[] bccs) {
        this.bcc.addAll(Arrays.asList(bccs));
        return this;
    }

    public EmailDTO setBcc(String[] bccs) {
        this.bcc = new ArrayList<String>(Arrays.asList(bccs));
        return this;
    }

    public String[] getBccs() {
        return this.bcc.toArray(new String[this.bcc.size()]);
    }

    public EmailDTO setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getSubject() {
        return this.subject;
    }

    public EmailDTO setText(String text) {
        this.text = text;
        return this;
    }

    public String getText() {
        return this.text;
    }

    public EmailDTO setHtml(String html) {
        this.html = html;
        return this;
    }

    public String getHtml() {
        return this.html;
    }

    public EmailDTO addSubstitution(String key, String[] val) {
        this.smtpapi.addSubstitutions(key, val);
        return this;
    }

    public JSONObject getSubstitutions() {
        return this.smtpapi.getSubstitutions();
    }

    public EmailDTO addUniqueArg(String key, String val) {
        this.smtpapi.addUniqueArg(key, val);
        return this;
    }

    public JSONObject getUniqueArgs() {
        return this.smtpapi.getUniqueArgs();
    }

    public EmailDTO addCategory(String category) {
        this.smtpapi.addCategory(category);
        return this;
    }

    public String[] getCategories() {
        return this.smtpapi.getCategories();
    }

    public EmailDTO addSection(String key, String val) {
        this.smtpapi.addSection(key, val);
        return this;
    }

    public JSONObject getSections() {
        return this.smtpapi.getSections();
    }

    public EmailDTO addFilter(String filter_name, String parameter_name, String parameter_value) {
        this.smtpapi.addFilter(filter_name, parameter_name, parameter_value);
        return this;
    }

    public JSONObject getFilters() {
        return this.smtpapi.getFilters();
    }

    public EmailDTO setASMGroupId(int val) {
        this.smtpapi.setASMGroupId(val);
        return this;
    }

    public Integer getASMGroupId() {
        return this.smtpapi.getASMGroupId();
    }

    public EmailDTO setSendAt(int sendAt) {
        this.smtpapi.setSendAt(sendAt);
        return this;
    }

    public int getSendAt() {
        return this.smtpapi.getSendAt();
    }

    /**
     * Convenience method to set the template
     *
     * @param templateId
     *            The ID string of your template
     * @return this
     */
    public EmailDTO setTemplateId(String templateId) {
        this.getSMTPAPI().addFilter("templates", "enable", 1);
        this.getSMTPAPI().addFilter("templates", "template_id", templateId);
        return this;
    }

    public EmailDTO addAttachment(String name, File file) throws IOException, FileNotFoundException {
        return this.addAttachment(name, new FileInputStream(file));
    }

    public EmailDTO addAttachment(String name, String file) throws IOException {
        return this.addAttachment(name, new ByteArrayInputStream(file.getBytes()));
    }

    public EmailDTO addAttachment(String name, InputStream file) throws IOException {
        this.attachments.put(name, file);
        return this;
    }

    public Map getAttachments() {
        return this.attachments;
    }

    public EmailDTO addContentId(String attachmentName, String cid) {
        this.contents.put(attachmentName, cid);
        return this;
    }

    public Map getContentIds() {
        return this.contents;
    }

    public EmailDTO addHeader(String key, String val) {
        this.headers.put(key, val);
        return this;
    }

    public Map getHeaders() {
        return this.headers;
    }

    public SMTPAPI getSMTPAPI() {
        return this.smtpapi;
    }

    @Override
    public String toString() {
        return "EmailDTO [to=" + to + ", toname=" + toname + ", cc=" + cc + ", from=" + from + ", fromname=" + fromname
                + ", replyto=" + replyto + ", subject=" + subject + ", text=" + text + ", html=" + html + "]";
    }
    
    
}