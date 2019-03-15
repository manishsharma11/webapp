package com.main.sts.dao.sql;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Commuter;
import com.main.sts.entities.Feedback;
import com.main.sts.entities.SMSCode;

@Repository
public class FeedbackDao extends BaseDao {

    public List<Feedback> findAll() {
        return getAllRecords(Feedback.class);
    }

    public Commuter getCommuter(int commuter_id) {
        String query = "from Commuter b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(Commuter.class, query, parameters);
    }

    public boolean addFeedback(Feedback feedback) {
        feedback.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(feedback);
    }

    public List<Feedback> getFeedbackForACommuter(int commuter_id) {
        String query = "from Feedback b where b.commuter_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(Feedback.class, query, parameters);
    }
    
    public BigInteger getFeedbackCount() {
        String query = "select count(id) from feedback";
        return getCountOFRecords(query);
    }
}
