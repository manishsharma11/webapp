package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Alerts;

@Repository
public class AlertsDao extends BaseDao {

    public void insertAlerts(Alerts address) {
        insertEntity(address);
    }

    public void updateAlerts(Alerts address) {
        updateEntity(address);
    }

    public Alerts getAlerts(int subscriber_id, String subscriber_type, String alert_type) {
        String query = "from Alerts b where b.subscriber_id=? and b.subscriber_type=? and b.alert_type=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, subscriber_id);
        parameters.put(1, subscriber_type);
        parameters.put(2, alert_type);
        return getSingleRecord(Alerts.class, query, parameters);
    }
}
