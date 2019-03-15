package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Parents;

@Repository
public class ParentDao  extends BaseDao{

	
	public List<Parents> getParent()
	{
		return getAllRecords(Parents.class);
	}

	public String getEmail(int subscriber_id) {
		 String query="from Parents p where p.student_id=?";
		 Map<Integer, Object> param=new HashMap<Integer, Object>();
		 param.put(0, subscriber_id);
		return getSingleRecord(Parents.class, query, param).getEmail();
	}
}
