package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.Guardians;

@Repository
public class GuardianDao extends BaseDao
{

	public List<Guardians> getGuardians(int s_id) {
		 String query="from Guardians g where g.student_id=?";
		 Map<Integer, Object> param=new HashMap<Integer, Object>();
		 param.put(0, s_id);
		return getRecords(Guardians.class, query, param);
	}

	public void addGuardian(Guardians guardians) {
		 insertEntity(guardians);
		
	}

	public Guardians getGuardianById(int id) {
		 
		 String query="from Guardians g where g.id=?";
		 Map<Integer, Object> param=new HashMap<Integer, Object>();
		 param.put(0, id);
		return getSingleRecord(Guardians.class, query, param);
	}

	public void updateGuardian(Guardians guardians) {
		 
		updateEntity(guardians);
	}

	
}
