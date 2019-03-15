package com.main.sts.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.main.sts.entities.PlexusMediatorRecord;

@Repository
public class PlexusMediatorDao extends BaseDao {

	public void insert(PlexusMediatorRecord mediatorRecord)
	{
		insertEntity(mediatorRecord);
	}

	public List<PlexusMediatorRecord> getStudents(Integer id,String date) {
		 String query="from PlexusMediatorRecord p where p.student_id=? and p.date=?";
		 Map<Integer, Object>param=new HashMap<Integer, Object>();
		 param.put(0, id);
		 param.put(1, date);
		return getRecords(PlexusMediatorRecord.class, query, param);
	}
	
	
}
