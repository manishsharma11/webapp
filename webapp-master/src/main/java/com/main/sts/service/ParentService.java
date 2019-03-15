package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.ParentDao;
import com.main.sts.entities.Parents;

@Service
public class ParentService {

    @Autowired
    private ParentDao parentdao;

    public List<Parents> getEmails() {
        return parentdao.getParent();
    }

    public String getEmailById(int subscriber_id) {
        return parentdao.getEmail(subscriber_id);
    }
}
