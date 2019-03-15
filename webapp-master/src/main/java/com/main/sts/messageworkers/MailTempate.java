package com.main.sts.messageworkers;

import org.springframework.stereotype.Component;

@Component
public class MailTempate {

	private MailTempate mailTempate;
	private String mailBody;
	private String subject;
	
	//import mailtemplate
	
	public MailTempate mailTemplateForStudentImport(String filename, int rows_inserted

			, int rows_failed, String msg) {

				String s = "<html><body>" + "<br> <h3>Student Import Information</h3>" + "<table>" + "<tr><td>File Name </td> <td><b>: " + filename
						+ "</b></td> </tr>" +  "<tr><td>Total Rows Inserted</td><td><b>: "
						+ rows_inserted + "</b></td></tr>" + "<tr><td> Total Rows Failed </td><td><b>: " + rows_failed + "</b></td></tr>"
						+ "<tr><td>Reason to Failed </td><td><b>: " +msg + "</b></td></tr>" 
						+ "</b></td></tr>" + "</table>" + "</body></html>";
				mailTempate = new MailTempate();
				mailTempate.setMailBody(s);
				mailTempate.setSubject(filename+" import Result");
				return mailTempate;
			}
	
	//end

	

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
}
