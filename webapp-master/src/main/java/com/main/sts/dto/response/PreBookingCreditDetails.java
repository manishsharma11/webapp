package com.main.sts.dto.response;

import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.FareDTO;

public class PreBookingCreditDetails {

    FareDTO fare;
    AccountDTO account;

    public FareDTO getFare() {
        return fare;
    }
    public void setFare(FareDTO fare) {
        this.fare = fare;
    }

    public AccountDTO getAccount() {
        return account;
    }
    public void setAccount(AccountDTO account) {
        this.account = account;
    }

}