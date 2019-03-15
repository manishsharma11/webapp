package com.main.sts.dao.sql;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Notification;

@Repository
public class NotificationDao extends BaseDao {

    public List<Notification> findAll() {
        return getAllRecords(Notification.class);
    }

    public boolean insertANotication(Notification notication) {
        notication.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(notication);
    }

    public boolean updateANotication(Notification notication) {
        return updateEntity(notication);
    }

    public boolean deleteNotication(Notification notication) {
        return deleteEntity(notication);
    }
}
