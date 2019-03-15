package com.main.sts.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.RechargeOptionsDao;
import com.main.sts.dto.RechargeOptionsRequest;
import com.main.sts.entities.Commuter;
import com.main.sts.entities.RechargeOptions;

@Service("rechargeOptionsService")
public class RechargeOptionsService {

    @Autowired
    private RechargeOptionsDao rechargeOptionsDao;
    
    @Autowired
    private CommuterService commuterService;
    

    public List<RechargeOptions> findAll() {
        return rechargeOptionsDao.findAll();
    }

    public RechargeOptions getRechargeOptions(int recharge_option_id) {
        return rechargeOptionsDao.findARechargeOption(recharge_option_id);
    }

    public List<RechargeOptions> getRechargeOptions(RechargeOptionsRequest req, boolean enabled) {
        Integer commuter_id = req.getCommuter_id();

        List<RechargeOptions> list = null;
        if (commuter_id != null) {
            Commuter c = commuterService.getCommuterById(commuter_id);
            Integer commuter_group_id = c.getCommuter_group_id();
            if (commuter_group_id != null) {
                list = rechargeOptionsDao.findAllRechargeOptionsForACommuterGroup(commuter_group_id, enabled);
            } else {
                list = rechargeOptionsDao.findAllRechargeOptions(enabled);
            }
        } else {
            list = rechargeOptionsDao.findAllRechargeOptions(enabled);
        }

        if (list != null) {
            Collections.sort(list, new RechargeComparator());
        } else {
            return new ArrayList<RechargeOptions>();
        }
        return list;
    }
    
    public List<RechargeOptions> getRechargeOptions(boolean enabled) {
        List<RechargeOptions> list = rechargeOptionsDao.findAllRechargeOptions(enabled);
        Collections.sort(list, new RechargeComparator());
        return list;
    }

    public Integer insertARechargeOption(RechargeOptions tx) {
        return rechargeOptionsDao.insertARechargeOption(tx);
    }

    public boolean updateRechargeOptions(int recharge_option_id, int recharge_amount, int num_credits_offered, boolean enabled) {
        return rechargeOptionsDao.updateARechargeOption(recharge_option_id, recharge_amount, num_credits_offered, enabled);
    }

    public static class RechargeComparator implements Comparator<RechargeOptions> {

        @Override
        public int compare(RechargeOptions o1, RechargeOptions o2) {
            return ((Integer) o1.getRecharge_amount()).compareTo((Integer) o2.getRecharge_amount());
        }
    }

}
