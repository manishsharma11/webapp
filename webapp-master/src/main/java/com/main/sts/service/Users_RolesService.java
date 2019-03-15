package com.main.sts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.Users_RolesDao;
import com.main.sts.entities.Users_Roles;

@Service
public class Users_RolesService 
{

	@Autowired
	private Users_RolesDao users_RolesDao;
	
	
	public void insertIntoUsers_roles(Users_Roles users_roles)
	{
		users_RolesDao.insertIntoUser_Roles(users_roles);
	}
	
	public Users_Roles getUserByUserId(int user_id)
	{
		return users_RolesDao.getUserByUserId(user_id);
	}
	public void updateUsersRoles(int user_id,int role_id)
	{
		Users_Roles ur=users_RolesDao.getUserByUserId(user_id);
		ur.setRole_id(role_id);
		users_RolesDao.updateUser_Roles(ur);
	}
	public boolean deleteUser_roles(int  user_id)
	{
		try{
		Users_Roles ur=getUserByUserId(user_id);
		users_RolesDao.deleteUser(ur);
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
