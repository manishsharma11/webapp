package com.main.sts;

import java.util.List;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.junit.Test;

import com.main.sts.common.CommonConstants.UserChannelType;
import com.main.sts.entities.Commuter;
import com.main.sts.service.CommuterService;
import com.main.sts.service.ReturnCodes;

public class CommuterServiceTest extends BaseTest {

	@Resource
	private CommuterService commuterService;

	@Test
	public void testFindAll() {
		Assert.assertFalse(commuterService.findAll().isEmpty());
		for (Commuter c : commuterService.findAll()) {
			System.out.println(c.getName());
		}
	}
	
	@Test
	public void testRegisterCommuter() {
		Assert.assertFalse(commuterService.findAll().isEmpty());
		String email ="rahul@easycommute.co";
		String mobile = "9908599937";
		String gcm_regid="test";
		String device_id = "";
		
		commuterService.registerCommuter("RahulJ", email, mobile, "M", gcm_regid, "", device_id, UserChannelType.MOBILE, false);
	}
	
	@Test
    public void testActivateCommuter() {
	    String email ="rahul@easycommute.co";
        String mobile = "990859993743";
        String gcm_regid="test";
        String device_id = "";

        commuterService.registerCommuter("RahulJ", email, mobile, "M", gcm_regid, "",device_id, UserChannelType.MOBILE, false);
        Assert.assertFalse(commuterService.findAll().isEmpty());
        // String email ="rahul@easycommute.co";
       // String mobile = "99085999374";
        int otpCode = 32208;

        ReturnCodes returnCodes = commuterService.verifyCommuter(mobile, otpCode, false);
        System.out.println("returnCodes:" + returnCodes);
    }

	@Test
	public void testFindById() {
		Assert.assertNotNull(commuterService.getCommuterByEmail("rahul@test.com"));
	}

	// @Test
	// public void testSaveEmployee(){
	// Employee employee = new Employee();
	// employee.setName("Flash gordan");
	//
	// List<Address> addresses = new ArrayList<Address>();
	// addresses.add(new Address(employee));
	//
	// employee.setAddresses(addresses);
	// commuterDao.save(employee);
	//
	// Assert.assertNotNull(commuterDao.findById(employee.getId()));
	// Assert.assertTrue(commuterDao.findById(employee.getId()).getAddresses().size()
	// > 0);
	// }

	// @Test
	// public void testEmployeeDelete(){
	// Employee employee = employeeDao.getEmployeeByName("Badal");
	// long id = employee.getId();
	// employeeDao.delete(id);
	// Assert.assertNull(employeeDao.findById(id));
	// }
	
//	@Test
//  public void testActivateCommuter() {
//    String email ="rahul@easycommute.co";
//      String mobile = "990859993743";
//      String gcm_regid="test";
//      
//      commuterService.registerCommuter("RahulJ", email, mobile, "M", gcm_regid, false);
//      Assert.assertFalse(commuterService.findAll().isEmpty());
//      // String email ="rahul@easycommute.co";
//     // String mobile = "99085999374";
//      int otpCode = 32208;
//
//      ReturnCodes returnCodes = commuterService.verifyCommuter(mobile, otpCode, false);
//      System.out.println("returnCodes:" + returnCodes);
//  }
	
    @Test
    public void testSendWelcomeSignupMessageSMS() {
        //int commuter_id = 764;
        String mobile = "9908599937";//"9887589396";
        boolean smsSendEnabled= false;
        //Commuter commuter = commuterService.getCommuterById(commuter_id);
        Commuter commuter = commuterService.getCommuterByMobile(mobile);
        boolean status = commuterService.sendWelcomeSignupMessageSMS(commuter, 3, false, smsSendEnabled);
        if (status) {
            System.out.println("status:" + status);
        }
    }
    
    @Test
    public void testSendWelcomeSignupMessageSMS_ExistingUser() {
        //int commuter_id = 764;
        String mobile = "9908599937";//"9887589396";
        boolean smsSendEnabled= false;
        //Commuter commuter = commuterService.getCommuterById(commuter_id);
        Commuter commuter = commuterService.getCommuterByMobile(mobile);
        boolean userRegistrationExist = true;
        boolean status = commuterService.sendWelcomeSignupMessageSMS(commuter, -1, userRegistrationExist, smsSendEnabled);
        if (status) {
            System.out.println("status:" + status);
        }
    }

}
