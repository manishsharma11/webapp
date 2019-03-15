package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.main.sts.entities.Users;

@Repository
public class UsersDao extends BaseDao {

	public List<Users> getAllUsers() {
		return getAllRecords(Users.class);
	}

	public Users getUser(int userid) {
		String query = "from Users u where u.user_id=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, userid);
		return getSingleRecord(Users.class, query, parameters);
	}

	@Transactional
	public void insertUser(Users rolesEntity) {

		insertEntity(rolesEntity);

	}

	public Users getUser(String user_name) {
		String query = "from Users u where u.user_name=?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, user_name);
		return getSingleRecord(Users.class, query, parameters);

	}

	public void updateUser(Users entity) {
		updateEntity(entity);
	}

	public void deleteUser(Users entity) {

		deleteEntity(entity);
	}

}
