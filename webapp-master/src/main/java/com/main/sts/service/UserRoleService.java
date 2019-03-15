package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.UserRoleDao;
import com.main.sts.entities.Roles;

@Service
public class UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	public void insertRole(Roles role) {

		userRoleDao.insertRole(role);
	}

	public void updateRole(Roles role) {

		userRoleDao.updateRole(role);

	}

	public List<Roles> getRoles() {
		return userRoleDao.getRoles();

	}

	public Roles getRoleNameById(int role_id) {

		return userRoleDao.getRole(role_id);
	}

	public void deleteRole(int role_id) {

		Roles role = getRoleNameById(role_id);
		userRoleDao.deleteRole(role);
	}

	public Roles isroleExist(String role_name) {

		return userRoleDao.getRoleByRoleName(role_name);
	}

}
