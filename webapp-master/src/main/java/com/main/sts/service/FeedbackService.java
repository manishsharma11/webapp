package com.main.sts.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.sts.dao.sql.FeedbackDao;
import com.main.sts.entities.Feedback;
import com.main.sts.entities.SuggestRoute;

@Service("feedbackService")
public class FeedbackService {
	@Autowired
	private FeedbackDao feedbackDao;

	public List<Feedback> findAll() {
		return feedbackDao.findAll();
	}

	public boolean addFeedback(Feedback feedback) {
		boolean success = feedbackDao.addFeedback(feedback);
		return success;
	}

	public List<Feedback> getFeedback(int commuter_id) {
		return feedbackDao.getFeedbackForACommuter(commuter_id);
	}

	 public BigInteger getFeedbackCount() {
       return feedbackDao.getFeedbackCount();
   }
	 
	 public List<Feedback> getRecordsByPagination(Integer offset, Integer limit) {
       return feedbackDao.getRecordsByPagination(Feedback.class, null, Order.desc("feedback_id"), offset, limit);
   }
}
