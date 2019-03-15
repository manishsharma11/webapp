package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.AdminEmails;

@Repository
public class AdminEmailDao extends BaseDao{

	public List<AdminEmails> getAllMails(){
		
		return (List<AdminEmails>) getAllRecords(AdminEmails.class);
	}
	
	public void addEmail(AdminEmails adminEmails){
		insertEntity(adminEmails);
	}
	public void deleteEmail(AdminEmails adminEmails){
		deleteEntity(adminEmails);
	}
	public AdminEmails getSingleMail(Integer id){
		String queryStr = "from AdminEmails d where d.id = ? ";
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		parameters.put(0, id);
		return getSingleRecord(AdminEmails.class, queryStr, parameters);
	}
}
