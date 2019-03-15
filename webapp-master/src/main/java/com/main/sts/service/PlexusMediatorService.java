package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import com.main.sts.dao.sql.PlexusMediatorDao;
import com.main.sts.entities.PlexusMediatorRecord;

@Service
public class PlexusMediatorService {

	
	@Autowired
	private PlexusMediatorDao plexusdao;
	
	public void insertRecord(PlexusMediatorRecord mediatorRecord)
	{
		plexusdao.insert(mediatorRecord);
	}

	public List<PlexusMediatorRecord> getStudentByStudId(Integer id,String date) {
		 
		return plexusdao.getStudents(id,date);
	}
}
