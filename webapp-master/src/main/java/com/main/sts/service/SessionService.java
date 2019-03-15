package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.SessionDao;
 
import com.main.sts.entities.Session;

@Service
public class SessionService
{

	@Autowired
	private SessionDao sessiondao;
	
	public void insertSession(Session session)
	{
		sessiondao.insert(session);
	}
	
	public void updateSession(Session session) {
		sessiondao.update(session);

	}

	public void deleteBus(Session session) {
		sessiondao.delete(session);
	}
	public List<Session> getSessions()
	{
		return sessiondao.getSessions();
	}
}
