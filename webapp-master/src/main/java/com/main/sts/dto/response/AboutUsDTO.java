package com.main.sts.dto.response;

import java.io.Serializable;

public class AboutUsDTO implements Serializable {

    public String about_us_desc;
    public String customer_care_email;
    public String customer_care_number;
    public String whatsapp_care_number;

    public String getAbout_us_desc() {
        return about_us_desc;
    }
    public void setAbout_us_desc(String about_us_desc) {
        this.about_us_desc = about_us_desc;
    }
    public String getCustomer_care_number() {
        return customer_care_number;
    }
    public void setCustomer_care_number(String customer_care_number) {
        this.customer_care_number = customer_care_number;
    }
    public String getWhatsapp_care_number() {
        return whatsapp_care_number;
    }
    public void setWhatsapp_care_number(String whatsapp_care_number) {
        this.whatsapp_care_number = whatsapp_care_number;
    }
    public String getCustomer_care_email() {
        return customer_care_email;
    }
    public void setCustomer_care_email(String customer_care_email) {
        this.customer_care_email = customer_care_email;
    }
}
