package com.main.sts.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AmazonSESExampleTest {

    static final String FROM = "support@easycommute.co";  // Replace with your "From" address. This address must be verified.
    static final String TO = "vengareddy@gmail.com"; // Replace with a "To" address. If you have not yet requested
                                                      // production access, this address must be verified.
    static final String BODY = "This email was sent through Amazon SES by using the AWS SDK for Java.";
    static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

    public static String getContent() {
        String content = null;
        try {
            String file = "/Users/rahul/Documents/easycommute/happy_new_year_email.html";
            file = "/Users/rahul/Documents/easycommute/html_mail_earlysignup.html";
            file = "/Users/rahul/Documents/easycommute/html_mail_wsj.html";
            content = FileUtils.readFileToString(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /*
     * Before running the code:
     *      Fill in your AWS access credentials in the provided credentials
     *      file template, and be sure to move the file to the default location
     *      (~/.aws/credentials) where the sample code will load the
     *      credentials from.
     *      https://console.aws.amazon.com/iam/home?#security_credential
     *
     * WARNING:
     *      To avoid accidental leakage of your credentials, DO NOT keep
     *      the credentials file in your source directory.
     */
    public static void main(String[] args) throws IOException {

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{TO});

        // Create the subject and body of the message.
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(BODY);
        //Body body = new Body().withText(textBody);
        String content = getContent();
        Content html = new Content().withData(content);
        Body body = new Body().withHtml(html);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);

        try {
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            /*
             * The ProfileCredentialsProvider will return your [default]
             * credential profile by reading from the credentials file located at
             * (~/.aws/credentials).
             *
             * TransferManager manages a pool of threads, so we create a
             * single instance and share it throughout our application.
             */
//            AWSCredentials credentials = null;
//            try {
//                credentials = new ProfileCredentialsProvider().getCredentials();
//            } catch (Exception e) {
//                throw new AmazonClientException(
//                        "Cannot load the credentials from the credential profiles file. " +
//                        "Please make sure that your credentials file is at the correct " +
//                        "location (~/.aws/credentials), and is in valid format.",
//                        e);
//            }
            
            final String secretKeyId = "AKIAJTEGXGNEHSHPE5OQ";
            final String secretKey = "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";

            AWSCredentials credentials = new AWSCredentials() {

                @Override
                public String getAWSSecretKey() {
                    return secretKey;
                }

                @Override
                public String getAWSAccessKeyId() {
                    return secretKeyId;
                }
            };

            // Instantiate an Amazon SES client, which will make the service call with the supplied AWS credentials.
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);

            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your production
            // access status, sending limits, and Amazon SES identity-related settings are specific to a given
            // AWS region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US East (N. Virginia) region. Examples of other regions that Amazon SES supports are US_WEST_2
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
            Region REGION = Region.getRegion(Regions.US_EAST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            System.out.println("Email sent!");

        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}