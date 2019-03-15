package com.main.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.AdminEmailDao;
import com.main.sts.entities.AdminEmails;

@Service
public class AdminEmailService {

	@Autowired
	private AdminEmailDao adminEmailDao;

	public List<AdminEmails> getAllMails() {
		return adminEmailDao.getAllMails();
	}
	
	public void addEmail(String email){
		
		AdminEmails adminEmails = new AdminEmails();
		adminEmails.setEmail(email);
		adminEmailDao.addEmail(adminEmails);
	}
	
	public void deleteEmail(Integer id){
		AdminEmails adminEmails = getMail(id);
		adminEmailDao.deleteEmail(adminEmails);
	}
	public AdminEmails getMail(Integer id){
		
		return adminEmailDao.getSingleMail(id);
	}
}
