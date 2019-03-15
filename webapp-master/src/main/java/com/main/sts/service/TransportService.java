package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.TransportDao;
import com.main.sts.entities.Transport;

@Service
public class TransportService {

    @Autowired
    private TransportDao transportdao;

    public Transport getTransport(int subscriber_id, String subscriber_type, String transport_type) {
        return transportdao.getTransport(subscriber_id, subscriber_type, transport_type);
    }
    public List<Transport> getCommuterIdByTrip(int trip_id) {
        return transportdao.getSubscriberId(trip_id, "user");
    }
}
