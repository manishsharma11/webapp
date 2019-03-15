package com.main.sts.dao.sql;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Session;

@Repository
public class SessionDao extends BaseDao {

    public void insert(Session session) {
        insertEntity(session);
    }

    public void update(Session session) {
        updateEntity(session);
    }

    public void delete(Session session) {
        deleteEntity(session);
    }

    public List<Session> getSessions() {
        return getAllRecords(Session.class);
    }

}
