package com.main.sts;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.main.sts.dto.response.ReferralDTO;
import com.main.sts.entities.ReferralCode;
import com.main.sts.service.ReferralCodeService;

public class ReferralCodeServiceTest extends BaseTest {

    @Resource
    private ReferralCodeService referralCodeService;

    @Test
    public void testGenerateReferralCode() {
        ReferralCode referralCode = referralCodeService.getOrGenerateReferralCode(1);
        Assert.assertNotNull(referralCode);
        for (ReferralCode c : referralCodeService.findAll()) {
            System.out.println(c.getId() + "--" + c.getCode() + "--" + c.getReferred_by());
        }
    }

    @Test
    public void testGetReferralCode() {
        ReferralDTO referralCode = referralCodeService.getReferralCode(1);
        Assert.assertNotNull(referralCode);
        System.out.println(referralCode.getId() + "--" + referralCode.getCode() + "--"
                + referralCode.getReferral_message() + "--" + referralCode.getReferral_scheme_desc());
    }

    @Test
    public void testFindAll() {
        Assert.assertFalse(referralCodeService.findAll().isEmpty());
        for (ReferralCode c : referralCodeService.findAll()) {
            System.out.println(c.getId() + "--" + c.getCode() + "--" + c.getReferred_by());
        }
    }

}
