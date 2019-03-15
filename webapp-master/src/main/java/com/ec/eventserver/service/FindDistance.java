package com.ec.eventserver.service;

import org.springframework.stereotype.Component;

@Component
public class FindDistance {

    public Double calculateDistance(String lat1, String long1, String lat2, String long2) {
        return calculateDistance(Double.parseDouble(lat1), Double.parseDouble(long1), Double.parseDouble(lat2),
                Double.parseDouble(long2));
    }
    
    public Double calculateDistance(Double lat1, Double long1, Double lat2, Double long2) {

        Double Lat1 = Math.toRadians(lat1);
        Double Lat2 = Math.toRadians(lat2);
        Double Long1 = Math.toRadians(long1);
        Double Long2 = Math.toRadians(long2);
        Double dlat = Lat2 - Lat1;
        Double dlon = Long2 - Long1;
        Double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(dlon / 2), 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)); // great
                                                                   // circle
                                                                   // distance
                                                                   // in
                                                                   // radians
        Double Rk = 6373.56;
        // Double dk = c * Rk;
        // long km = Math.round(dk);
        // System.out.println("km: "+dk);
        return c * Rk;
    }
}
