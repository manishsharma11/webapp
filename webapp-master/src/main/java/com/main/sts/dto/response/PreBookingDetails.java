package com.main.sts.dto.response;

import java.util.List;

import com.main.sts.dto.AccountDTO;
import com.main.sts.dto.FareDTO;

public class PreBookingDetails {
    
    List<StopsResponseDTO> stops;
    FareDTO fare;
    AccountDTO account;

    public List<StopsResponseDTO> getStops() {
        return stops;
    }
    public void setStops(List<StopsResponseDTO> stops) {
        this.stops = stops;
    }
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
    @Override
    public String toString() {
        return "PreBookingDetails [stops=" + stops + ", fare=" + fare + "]";
    }
}