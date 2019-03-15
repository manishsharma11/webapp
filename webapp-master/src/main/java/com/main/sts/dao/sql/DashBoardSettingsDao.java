package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.DashboardSettings;

@Repository
public class DashBoardSettingsDao extends BaseDao {

    public DashboardSettings getDashBoardSettings(int id) {
        String queryStr = "from DashboardSettings d where d.id = ? ";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, id);
        return getSingleRecord(DashboardSettings.class, queryStr, parameters, true);
    }

    public void updateSettings(DashboardSettings dashboardSettings) {
        updateEntity(dashboardSettings);
    }

    public void saveSettings(DashboardSettings dashboardSettings) {
        insertEntity(dashboardSettings);
    }
}
