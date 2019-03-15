package com.main.sts.dao.sql;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.CorporateReferralCode;
import com.main.sts.entities.ReferralCode;

@Repository
public class ReferralCodeDao extends BaseDao {

    public List<ReferralCode> findAll() {
        return getAllRecords(ReferralCode.class);
    }

    public boolean insertAReferralCode(ReferralCode referralCode) {
        return insertEntity(referralCode);
    }

    public boolean updateAReferralCode(ReferralCode referralCode) {
        return updateEntity(referralCode);
    }

    public boolean deleteReferralCode(ReferralCode referralCode) {
        return deleteEntity(referralCode);
    }

    public ReferralCode getReferralCodeById(int referral_code_id) {
        String query = "from ReferralCode b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referral_code_id);
        return getSingleRecord(ReferralCode.class, query, parameters);
    }

    public ReferralCode getReferralCodeByCode(String referral_code) {
        String query = "from ReferralCode b where b.code=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referral_code);
        return getSingleRecord(ReferralCode.class, query, parameters);
    }

    public ReferralCode getReferralCodeByCommuterId(int commuter_id) {
        String query = "from ReferralCode b where b.referred_by=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, commuter_id);
        return getSingleRecord(ReferralCode.class, query, parameters, 1);
    }
    
    
    public CorporateReferralCode getCorporateReferralCodeByCode(String referral_code) {
        String query = "from CorporateReferralCode b where b.code=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, referral_code);
        return getSingleRecord(CorporateReferralCode.class, query, parameters);
    }
    
    ///=========== All Corporate referral codes
    public List<CorporateReferralCode> findAllCorporateReferralCodes() {
        return getAllRecords(CorporateReferralCode.class);
    }

    public boolean insertACorporateReferralCode(CorporateReferralCode referralCode) {
        return insertEntity(referralCode);
    }

    public boolean updateACorporateReferralCode(CorporateReferralCode referralCode) {
        return updateEntity(referralCode);
    }

    public boolean deleteCorporateReferralCode(CorporateReferralCode referralCode) {
        return deleteEntity(referralCode);
    }
		
    public int getCommuterByReferralCode(String referral_code) {
       String query = "select referred_by from referral_codes where code = '" + referral_code +"'";
       return getCountOFRecords(query).intValue();
    }
}