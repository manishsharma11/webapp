package com.main.sts.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.common.CommonConstants;
import com.main.sts.common.CommonConstants.ReferralCodeStatus;
import com.main.sts.dao.sql.ReferralCodeDao;
import com.main.sts.dto.response.ReferralDTO;
import com.main.sts.entities.CorporateReferralCode;
import com.main.sts.entities.ReferralCode;

@Service("referralCodeService")
public class ReferralCodeService {

    @Autowired
    private ReferralCodeDao referralCodeDao;
    
    @Autowired
    private DashBoardSettingsService settingsService;

    public List<ReferralCode> findAll() {
        return referralCodeDao.findAll();
    }

    public ReferralCode getReferralCodeById(int referral_code_id) {
        return referralCodeDao.getReferralCodeById(referral_code_id);
    }

    public ReferralCode getReferralCodeByCode(String referral_code) {
        return referralCodeDao.getReferralCodeByCode(referral_code);
    }

    public ReferralCode getReferralCodeByCommuterId(int commuter_id) {
        return referralCodeDao.getReferralCodeByCommuterId(commuter_id);
    }
    
    public CorporateReferralCode getCorporateReferralCodeByCode(String referral_code) {
        return referralCodeDao.getCorporateReferralCodeByCode(referral_code);
    }

    public boolean insertAReferralCode(ReferralCode code) {
        return referralCodeDao.insertAReferralCode(code);
    }

    public ReferralDTO getReferralCode(int commuter_id) {
        ReferralCode refferral = getOrGenerateReferralCode(commuter_id);
        ReferralDTO r = new ReferralDTO();

        String default_referral_message = CommonConstants.REFERRAL_MESSAGE;
        
        String referral_message = settingsService.getDashBoardSettings().getReferral_message();
        if (referral_message == null) {
            referral_message = default_referral_message;
        }
        String referralMsg = String.format(referral_message, refferral.getCode());

        r.id = refferral.getId();
        r.code = refferral.getCode();
        r.referral_message = referralMsg;
        r.referral_scheme_desc = settingsService.getReferralScheme();

        // r.created_at = refferral.getCreated_at();
        return r;
    }
    
    public int getCommuterByReferralCode(String referral_code) {
        return referralCodeDao.getCommuterByReferralCode(referral_code);
    }
    
    public ReferralCode getOrGenerateReferralCode(int commuter_id) {

        // If for a person, referral code already exists.
        ReferralCode existingCommuterReferralCode = getReferralCodeByCommuterId(commuter_id);
        if (existingCommuterReferralCode != null) {
            return existingCommuterReferralCode;
        }

        // Assumption is that referral code is not generated for that user that
        // he can share.
        ReferralCode referralCode = new ReferralCode();
        referralCode.setReferred_by(commuter_id);
        // generating a unique code.
        String generatedCode = generateUniqueCode(4);
        referralCode.setCode(generatedCode);
        referralCode.setReferral_code_status(ReferralCodeStatus.ACTIVE.getReferralCodeStatus());
        referralCode.setCreated_at(new Timestamp(new Date().getTime()));

        int maxtry = 10;
        int numtry = 0;

        String uniqueCode = null;
        while (numtry <= maxtry) {
            ReferralCode existingCode = getReferralCodeByCode(referralCode.getCode());

            if (existingCode != null) {
                uniqueCode = generateUniqueCode(4);
                referralCode.setCode(uniqueCode);
                existingCode = getReferralCodeByCode(referralCode.getCode());
            } else {
                break;
            }
            numtry++;
        }

        boolean status = referralCodeDao.insertAReferralCode(referralCode);
        if (status) {
            return referralCode;
        } else {
            return null;
        }
    }

//    private String generateUniqueCode() {
//        String prefix = "EC";
//        String code = "AXcY";
//        // TODO: remove this. code should be only 4 character excluding EC
//        // including a-z,A-Z,1-9
//        return prefix + Long.toHexString(System.currentTimeMillis()).substring(6);
//        // return prefix + code;
//    }
    
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rnd = new Random();

    public String generateUniqueCode(int len) {
        String prefix = "EC";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return prefix + sb.toString();
    }

    public boolean updateAReferralCode(int referral_code_id, String question, String answer) {
        ReferralCode referralCode = getReferralCodeById(referral_code_id);
        // referralCode.setReferral_code_status(referral_code_status);
        return referralCodeDao.updateAReferralCode(referralCode);
    }

    public boolean deleteReferralCode(int referral_code_id) {
        ReferralCode referralCode = getReferralCodeById(referral_code_id);
        return referralCodeDao.deleteReferralCode(referralCode);
    }

    public static void main(String[] args) {
        System.out.println(Long.toHexString(System.currentTimeMillis()));
    }
}
