package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Users_Roles;

@Repository
public class Users_RolesDao extends BaseDao {

    public void insertIntoUser_Roles(Users_Roles users_roles) {
        insertEntity(users_roles);
    }

    public Users_Roles getUserByUserId(int user_id) {
        String queString = "from Users_Roles u where u.user_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, user_id);
        return getSingleRecord(Users_Roles.class, queString, parameters);
    }

    public Users_Roles getUserByRoleId(int role_id) {
        String queString = "from Users_Roles u where u.role_id=?";
        Map<Integer, Object> parameters = new HashMap<Integer, Object>();
        parameters.put(0, role_id);
        return getSingleRecord(Users_Roles.class, queString, parameters);
    }

    public void deleteUser(Users_Roles user) {
        deleteEntity(user);
    }

    public List<Users_Roles> getRecords() {
        return getAllRecords(Users_Roles.class);
    }

    public void updateUser_Roles(Users_Roles users_Roles) {
        updateEntity(users_Roles);
    }

}
