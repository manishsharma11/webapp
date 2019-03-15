package com.main.sts.dao.sql;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.UserActiveType;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.SMSCode;
import com.main.sts.entities.Trips;

@Repository
public class CommuterDao extends BaseDao {

    public BigInteger getCommutersCount() {
        String query = "select count(id) from commuters";
        return this.getCountOFRecords(query);
    }
    
    // PAY ATTENTION BEFORE USING : It will fetch all commuter details in one call.
    public List<Commuter> findAll() {
        return getAllRecords(Commuter.class, Order.asc("commuter_id"));
    }
    
    public List<Commuter> findAllLatestFirst() {
        return getAllRecords(Commuter.class, Order.desc("created_at"));
    }
    
    public List<Commuter> findAllOldestFirst() {
        return getAllRecords(Commuter.class, Order.asc("commuter_id"));
    }
    
    public List<Commuter> findAll(int offset, int limit) {
        String query = "from Commuter b ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        return getRecords(Commuter.class, query, parameters, offset, limit, true);
    }
    
    public List<Commuter> findAllActiveAndVerified() {
        String query = "from Commuter b where b.active=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, CommonConstants.UserActiveType.ACTIVE);
        return getRecords(Commuter.class, query, parameters, 0, 0, true);
    }
    
    public List<Commuter> findAllActiveAndVerified(int offset, int limit) {
        String query = "from Commuter b where b.active=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, CommonConstants.UserActiveType.ACTIVE);
        return getRecords(Commuter.class, query, parameters, offset, limit, true);
    }

    public Commuter getCommuter(int commuter_id) {
        String query = "from Commuter b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(Commuter.class, query, parameters, true);
    }

    public boolean insertCommuter(Commuter commuter) {
        commuter.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(commuter);
    }

    public Commuter getCommuterByMobile(String mobileNo) {
        String query = "from Commuter b where b.mobile=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, mobileNo);
        return getSingleRecord(Commuter.class, query, parameters, true);
    }

    public Commuter getCommuterByEmail(String email) {
        String query = "from Commuter b where b.email=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, email);
        return getSingleRecord(Commuter.class, query, parameters, true);
    }

    public boolean updateCommuter(Commuter commuter) {
        commuter.setUpdated_at(new Timestamp(new Date().getTime()));
        return updateEntity(commuter);
    }

    public void enableCommuter(int commuter_id) {
        changeCommuterStatus(commuter_id, UserActiveType.ACTIVE);
    }

    public boolean disableCommuter(int commuter_id) {
        return changeCommuterStatus(commuter_id, UserActiveType.BLOCKED);
    }

    public boolean changeCommuterStatus(int commuter_id, UserActiveType type) {
        Commuter commuter = getCommuter(commuter_id);
        commuter.active = type.getTypeCode();
        commuter.setUpdated_at(new Timestamp(new Date().getTime()));
        return updateEntity(commuter);
    }

    public boolean deleteCommuter(Commuter commuter) {
        return deleteEntity(commuter);
    }

    public List<Commuter> searchCommuters(String str, String type) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(type, "%" + str + "%");
        return searchRecords(Commuter.class, restrictions);
    }

    public List<Commuter> searchCommuters(String columnName, Integer val) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(columnName, val);
        return searchRecords(Commuter.class, restrictions);
    }
    
    public boolean deleteSMSCode(SMSCode smsCode) {
        return deleteEntity(smsCode);
    }

    public boolean deleteSMSCodeByCommuterId(int commuter_id) {
        List<SMSCode> smsCodes = getSMSCodeByCommuterId(commuter_id);
        boolean deleted = true;
        for (SMSCode smsCode : smsCodes) {
            System.out.println("deleting entry for smscode id:" + smsCode.getId());
            boolean status = deleteSMSCode(smsCode);
            deleted = deleted && status;
        }
        return deleted;
    }

    public boolean insertSMSCode(int commuter_id, int otp) {
        SMSCode smsCode = new SMSCode();
        smsCode.setCommuter_id(commuter_id);
        smsCode.setCode(otp);
        smsCode.setStatus(0);
        smsCode.setCreated_at(new Timestamp(new Date().getTime()));
        boolean status = insertEntity(smsCode);
        return status;
    }

    public List<SMSCode> getSMSCodeByCommuterId(int commuter_id) {
        String query = "from SMSCode b where b.commuter_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getRecords(SMSCode.class, query, parameters);
    }

    // TODO: Fix this part, as it can result in SQL injection attack.
    public Commuter activateCommuter(int commuter_id, int otpCode) {
        String query = "from Commuter c , SMSCode s where s.commuter_id = c.id AND c.id= ? AND s.code = ? order by s.created_at desc ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        parameters.put(1, otpCode);
        Object[] arr = getSingleRecord(Object[].class, query, parameters, 1);
        Commuter commuter = (Commuter) arr[0];
        return commuter;
    }

    public Object[] getCommuterWithOTPCode(int commuter_id) {
        String query = "from Commuter c , SMSCode s where s.commuter_id = c.id AND c.id= ? order by s.created_at desc";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        Object[] arr = getSingleRecord(Object[].class, query, parameters, 1);
        // Commuter commuter = (Commuter) arr[0];
        // return commuter;
        return arr;
    }

    public boolean insertSMSCode(SMSCode smsCode) {
        smsCode.setCreated_at(new Timestamp(new Date().getTime()));
        return insertEntity(smsCode);
    }
    
    public List<Commuter> getCommutersList(Integer offset, Integer limit) {
        Order orderby = Order.asc("commuter_id");
        return this.getRecordsByPagination(Commuter.class, null, orderby, offset, limit);
    }

}
