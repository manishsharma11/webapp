package com.main.sts.dto.response;

import java.io.Serializable;

public class OfferCheck implements Serializable {

    public String offer_message;
    public boolean enabled_offer_check;

    public String getOffer_message() {
        return offer_message;
    }
    public void setOffer_message(String offer_message) {
        this.offer_message = offer_message;
    }
    public boolean isEnabled_offer_check() {
        return enabled_offer_check;
    }
    public void setEnabled_offer_check(boolean enabled_offer_check) {
        this.enabled_offer_check = enabled_offer_check;
    }

}
