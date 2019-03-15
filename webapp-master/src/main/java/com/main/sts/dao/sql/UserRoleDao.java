package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Roles;

@Repository
public class UserRoleDao extends BaseDao {

    public void insertRole(Roles role) {
        insertEntity(role);
    }

    public void updateRole(Roles role) {
        updateEntity(role);
    }

    public List<Roles> getRoles() {
        return getAllRecords(Roles.class);
    }

    public Roles getRoleByRoleName(String role_name) {
        String querString = "from Roles r where r.role_name =?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, role_name);
        return getSingleRecord(Roles.class, querString, parameters);
    }

    public Roles getRole(int role_id) {
        String queryStr = "from Roles r where r.role_id =?";
        Map<Integer, Object> parameter = new HashMap<Integer, Object>();
        parameter.put(0, role_id);
        return getSingleRecord(Roles.class, queryStr, parameter);
    }

    public void deleteRole(Roles role) {
        deleteEntity(role);
    }

}
