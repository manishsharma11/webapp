package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.UsersDao;
import com.main.sts.entities.Users;
import com.main.sts.util.MD5PassEncryptionClass;

@Service
public class UserService {

	@Autowired
	private MD5PassEncryptionClass  encryptionClass;
	@Autowired
	private UsersDao usersDao;

	public List<Users> getAllUsers() {
		return usersDao.getAllUsers();
	}

	public Users isUserExist(int userid) {

		return usersDao.getUser(userid);

	}

	public void addUser(Users user) {

		String pass = user.getPassword();
		user.setPassword(encryptionClass.EncryptText(pass));
		usersDao.insertUser(user);

	}

	public Users isUserNameExist(String user_name) {
		return usersDao.getUser(user_name);
	}

	public void updateUser(String full_name,String user_name, String access_status, int user_id) {
		Users u = isUserExist(user_id);
		u.setFull_name(full_name);
		u.setAccess_status(access_status);
		u.setUser_name(user_name);
		u.setPassword(u.getPassword());
		usersDao.updateUser(u);
	}

	public boolean deleteUser(int user_id) {
		try {
			Users u = isUserExist(user_id);
			usersDao.deleteUser(u);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void changePassword(Users user){
		usersDao.updateUser(user);
	}

	public Users getUserById(int parseInt) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean validatePassword(String rawPass, String hashPass) {
		String rawPasshashedPassword = encryptionClass.EncryptText(rawPass);
		// System.out.println(rawPasshashedPassword);
		if (rawPasshashedPassword.equals(hashPass)) {
			return true;
		} else
			return false;
	}
}
