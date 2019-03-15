package com.main.sts;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.entities.Feedback;
import com.main.sts.entities.RouteStops;
import com.main.sts.entities.Stops;
import com.main.sts.service.FeedbackService;
import com.main.sts.service.RouteStopsService;

public class FeedbackServiceTest extends BaseTest {

	@Resource
	private FeedbackService feedbackService;

	@Test
	public void testFindAll() {
		//Assert.assertFalse(feedbackService.findAll().isEmpty());
		for (Feedback c : feedbackService.findAll()) {
			System.out.println(c.getReason());
		}
	}
	
//	@Test
//	public void testFindAllAvailableToStops() {
//		int stop_id = 741;
//		Set<RouteStops> routeStops = feedback.getAllAvailableToStops(stop_id);
//		for (RouteStops rs : routeStops) {
//			System.out.println(rs.getId()+"--"+rs.getStop_number() +"--"+rs.getStop().getStop_name());
//		}
//	}
//	
	

}
