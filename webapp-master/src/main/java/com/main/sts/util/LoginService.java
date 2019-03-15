package com.main.sts.util;

import java.util.HashMap;
import java.util.Map;





import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.main.sts.dao.sql.BaseDao;
import com.main.sts.entities.Users;

@Repository
@Transactional
public class LoginService extends BaseDao {

	@Transactional
	public Users getUser(String login) {

		String query = "from Users u where u.user_name = ?";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, login);
		return getSingleRecord(Users.class, query, parameters);

	}

	public void insertUser(Users user) {

		insertEntity(user);
	}
}
