package com.main.sts.service;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.CommuterDao;
import com.main.sts.entities.SMSCode;

@Service("smsCodeService")
public class SmsCodeService {

    @Autowired
    private CommuterDao commuterDao;

    public boolean insertSMSCode(SMSCode smsCode) {
        smsCode.setCreated_at(new Timestamp(new Date().getTime()));
        return commuterDao.insertSMSCode(smsCode);
    }

}
