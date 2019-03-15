/**
 * 
 */
package com.main.sts.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.main.sts.entities.DashboardSettings;

public class MyThreadPoolExecutor {

	private static final Logger logger = Logger.getLogger(MyThreadPoolExecutor.class);

	String toAddress1, subject1, sendMesg1;
	Session session1;
	DashboardSettings adminPreferences1;
	ArrayList<String> mobile1 = new ArrayList<String>();
	String msg;
	static String res = "";
	int poolSize = 2;

	int maxPoolSize = 2;

	long keepAliveTime = 10;

	ThreadPoolExecutor threadPool = null;

	final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10);

	public MyThreadPoolExecutor() {
		threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, queue);

	}

	public void runTask(Runnable task) {
		// System.out.println("Task count.."+threadPool.getTaskCount() );
		// System.out.println("Queue Size before assigning the
		// task.."+queue.size() );
		threadPool.execute(task);
		// System.out.println("Queue Size after assigning the
		// task.."+queue.size() );
		// System.out.println("Pool Size after assigning the
		// task.."+threadPool.getActiveCount() );
		// System.out.println("Task count.."+threadPool.getTaskCount() );
		System.out.println("Task count.." + queue.size());

	}

	public void shutDown() {
		threadPool.shutdown();
	}

	public String sendMailThread(String toAddress, String subject, String sendMesg, Session session,
			DashboardSettings adminPreferences) {
		// System.out.println(adminPreferences);

		this.session1 = session;
		this.toAddress1 = toAddress;
		this.subject1 = subject;
		this.sendMesg1 = sendMesg;
		this.adminPreferences1 = adminPreferences;

		try {

			MyThreadPoolExecutor emailThreadPoolExecutor = new MyThreadPoolExecutor();
			emailThreadPoolExecutor.runTask(new Runnable() {
				public void run() {
					try {
						System.out.println("session " + toAddress1);

						Message message = new MimeMessage(session1);

						message.setFrom(new InternetAddress(adminPreferences1.getFrom_email(), "PlexusTeam"));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress1));
						message.setSubject(subject1);
						message.setContent(sendMesg1, "text/html");
						Transport.send(message);
						// Thread.sleep(3000);
						// System.out.println("completed");
						logger.info("sent mail to [ " + toAddress1 + " ] about [ " + subject1 + " ]");
						// MyThreadPoolExecutor.res="success";
					} catch (AddressException ae) {
						ae.printStackTrace();
						// MyThreadPoolExecutor.res="failure";
					} catch (MessagingException me) {
						// TODO Auto-generated catch block
						me.printStackTrace();
						// MyThreadPoolExecutor.res="failure";
					} catch (Exception e) {
						e.printStackTrace();
						// MyThreadPoolExecutor.res="failure";
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
			// MyThreadPoolExecutor.res="failure";
		}

		// System.out.println("res in thread "+MyThreadPoolExecutor.res);

		return res;
	}

	public void sendMailThreadWithAttach(final String toAddress, final String subject, final String sendMesg,
			final Session session, final DashboardSettings adminPreferences, final File file, final File csvfile) {

		// System.out.println(adminPreferences);
		this.session1 = session;
		this.toAddress1 = toAddress;
		this.subject1 = subject;
		this.sendMesg1 = sendMesg;
		this.adminPreferences1 = adminPreferences;

		try {

			MyThreadPoolExecutor emailThreadPoolExecutor = new MyThreadPoolExecutor();
			emailThreadPoolExecutor.runTask(new Runnable() {
				public void run() {
					try {
						Message message = new MimeMessage(session1);
						message.setFrom(new InternetAddress(adminPreferences1.getFrom_email()));
						message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress1));
						message.setSubject(subject1);

						/*
						 * DataSource source = new
						 * FileDataSource(file.getAbsolutePath());
						 * message.setDataHandler(new DataHandler(source));
						 * 
						 * logger.info("Absolute path of file is" +
						 * file.getAbsolutePath()); Multipart multipart = new
						 * MimeMultipart(); BodyPart messageBodyPart = new
						 * MimeBodyPart(); //messageBodyPart.setText(sendMesg);
						 * message
						 * .setFileName(file.getAbsolutePath().toString());
						 * multipart.addBodyPart(messageBodyPart);
						 * logger.info("Added attachment to email"); // Send the
						 * complete message parts message.setContent(multipart);
						 * Transport.send(message); // Thread.sleep(1000);
						 * file.delete();
						 */

						Message msg = new MimeMessage(session);

						msg.setFrom(new InternetAddress(adminPreferences.getFrom_email()));
						InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
						msg.setRecipients(Message.RecipientType.TO, toAddresses);
						msg.setSubject(subject);
						msg.setSentDate(new Date());

						// creates message part
						// MimeBodyPart messageBodyPart = new MimeBodyPart();

						// creates multi-part
						Multipart multipart = new MimeMultipart();
						BodyPart htmlPart = new MimeBodyPart();
						htmlPart.setContent("<html><b>" + sendMesg + "</b></html>", "text/html");
						// adds attachments
						MimeBodyPart attachPart = new MimeBodyPart();

						attachPart.attachFile(file.getAbsoluteFile());

						multipart.addBodyPart(attachPart);
						multipart.addBodyPart(htmlPart);

						// csv attachment by sami only below code added

						MimeBodyPart csvattach = new MimeBodyPart();
						csvattach.attachFile(csvfile.getAbsoluteFile());
						multipart.addBodyPart(csvattach);

						// ended

						// sets the multi-part as e-mail's content
						msg.setContent(multipart);

						// sends the e-mail
						Transport.send(msg);

						csvfile.delete();
					} catch (AddressException ae) {
						// TODO Auto-generated catch block
						ae.printStackTrace();
					} catch (MessagingException me) {
						// TODO Auto-generated catch block
						me.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("sent mail to [ " + toAddress + " ] with report as an attachment [ " + subject + " ]");
	}

	@SuppressWarnings("deprecation")
	public void sendSmsToIndiaThread(ArrayList<String> mobile, String message) {
		// System.out.println(adminPreferences);
		this.mobile1 = mobile;
		this.msg = message;

		try {

			MyThreadPoolExecutor smsThreadPoolExecutor = new MyThreadPoolExecutor();
			smsThreadPoolExecutor.runTask(new Runnable() {
				public void run() {
					try {

						String authkey = "1700AMGRNUICF52eb9179";

						logger.info("Sending messages to :  " + mobile1.toString());

						String senderId = "slabss";

						ArrayList<String> al = mobile1;

						String numbers = "";
						for (String s : al) {
							numbers += s + ",";
						}

						// Your message to send, Add URL endcoding here.
						/* String message = "from string Some Test message"; */

						// define route
						String route = "default";

						// Prepare Url
						URLConnection myURLConnection = null;
						URL myURL = null;
						@SuppressWarnings("unused")
						BufferedReader buffreader = null;

						// encoding message
						String encoded_message = URLEncoder.encode(msg);

						// Send SMS API
						String mainUrl = "http://login.bulksmsglobal.in/sendhttp.php?";

						// Prepare parameter string
						StringBuilder sbPostData = new StringBuilder(mainUrl);
						sbPostData.append("authkey=" + authkey);
						sbPostData.append("&mobiles=" + numbers);
						sbPostData.append("&message=" + encoded_message);
						sbPostData.append("&route=" + route);
						sbPostData.append("&sender=" + senderId);

						// final string
						mainUrl = sbPostData.toString();

						// prepare connection
						myURL = new URL(mainUrl);
						myURLConnection = myURL.openConnection();
						myURLConnection.connect();
						buffreader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));

						logger.info("Sms sent...");
						// finally close connection

					} catch (IOException ie) {
						ie.printStackTrace();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("sent sms about [ " + msg + " ]");
	}

}
