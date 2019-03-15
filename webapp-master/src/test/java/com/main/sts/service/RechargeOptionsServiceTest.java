package com.main.sts.service;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.BaseTest;
import com.main.sts.entities.RechargeOptions;

public class RechargeOptionsServiceTest extends BaseTest {

    @Resource
    private RechargeOptionsService rechargeOptionsService;

    @Test
    public void getAllRechageOptions() {
        List<RechargeOptions> rechargeOptions = rechargeOptionsService.getRechargeOptions(true);
        for (RechargeOptions r : rechargeOptions) {
            System.out.println(r.getRecharge_amount() + "--" + r.getNum_credits_offered());
        }
    }

    @Test
    public void insertARechargeOption() {
        RechargeOptions r = new RechargeOptions();
        r.setEnabled(true);
        r.setRecharge_amount(20);
        r.setNum_credits_offered(20);
        Integer id = rechargeOptionsService.insertARechargeOption(r);
        System.out.println(id);
        Assert.assertNotNull(id);;
        Assert.assertNotSame(-1, id);;
    }

    @Test
    public void updateARechargeOption() {
        boolean enabled = true;
        int recharge_amount = 12;
        int num_credits_offered = 5;
        int recharge_option_id = 7;
        boolean status = rechargeOptionsService.updateRechargeOptions(recharge_option_id, recharge_amount,
                num_credits_offered, enabled);
        System.out.println(status);

        RechargeOptions ro = rechargeOptionsService.getRechargeOptions(recharge_option_id);
        System.out.println(ro.getRecharge_amount());
        Assert.assertEquals(ro.getRecharge_amount(), recharge_amount);
    }
}
