package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Transport;

@Repository
public class TransportDao extends BaseDao {

    public Transport getTransport(int subscriber_id, String subscriber_type, String transport_type) {
        String queryStr = "from Transport b where b.subscriber_id=? and b.subscriber_type=? and b.transport_type=? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, subscriber_id);
        parameters.put(1, subscriber_type);
        parameters.put(2, transport_type);
        return getSingleRecord(Transport.class, queryStr, parameters);
    }

    public List<Transport> getSubscriberId(int trip_id, String s) {
        String auery = "from Transport b where b.subscriber_type=? and trip_id=?";
        Map<Integer, Object> param = new HashMap<Integer, Object>();
        param.put(0, s);
        param.put(1, trip_id);
        return getRecords(Transport.class, auery, param);
    }
}
