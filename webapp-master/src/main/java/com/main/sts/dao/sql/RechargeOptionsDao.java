package com.main.sts.dao.sql;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.RechargeOptions;

@Repository
public class RechargeOptionsDao extends BaseDao {

    public List<RechargeOptions> findAll() {
        return getAllRecords(RechargeOptions.class);
    }

    public RechargeOptions findARechargeOption(int recharge_option_id) {
        String query = "from RechargeOptions b where b.id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, recharge_option_id);
        return getSingleRecord(RechargeOptions.class, query, parameters);
    }

    public List<RechargeOptions> findAllRechargeOptions(boolean enabled) {
        String query = "from RechargeOptions b where b.enabled=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, enabled);
        return getRecords(RechargeOptions.class, query, parameters);
    }
    
    public List<RechargeOptions> findAllRechargeOptionsForACommuterGroup(int commuter_group_id, boolean enabled) {
        String query = "from RechargeOptions b where b.enabled=? AND b.commuter_group_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, enabled);
        parameters.put(1, commuter_group_id);
        return getRecords(RechargeOptions.class, query, parameters);
    }


    public boolean updateARechargeOption(int recharge_option_id, int recharge_amount, int num_credits_offered,
            boolean enabled) {
        RechargeOptions rechargeOption = findARechargeOption(recharge_option_id);
        rechargeOption.setRecharge_amount(recharge_amount);
        rechargeOption.setNum_credits_offered(num_credits_offered);
        rechargeOption.setEnabled(enabled);
        rechargeOption.setUpdated_at(new Date());
        return updateEntity(rechargeOption);
    }

    public Integer insertARechargeOption(RechargeOptions rechargeOptions) {
        rechargeOptions.setCreated_at(new Date());
        return (Integer) insertEntityAndGetId(rechargeOptions);
    }

}
