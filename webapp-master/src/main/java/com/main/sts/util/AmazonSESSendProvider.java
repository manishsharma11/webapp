package com.main.sts.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import com.main.sts.dto.EmailDTO;
import com.main.sts.dto.EmailResponse;
import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

@Service("amazonSESEmailService")
public class AmazonSESSendProvider {

    final String secretKeyId = "AKIAJTEGXGNEHSHPE5OQ";
    final String secretKey = "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";
    
    //Name <info@gitgrow.com>
    //String from = "EasyCommuteApp <support@easycommute.co>";
    String from = "EasyCommute <announcement@easycommute.co>";

    public static void main(String[] args) throws IOException {

    }
    
    public AWSCredentials getCredentials() {
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
        return credentials;
    }

    public EmailResponse sendEmail(EmailDTO emailObj) {
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(emailObj.getTos());

        // Create the subject and body of the message.
        Content subject = new Content().withData(emailObj.getSubject());
        Content html = new Content().withData(emailObj.getHtml());
        Body body = new Body().withHtml(html);

        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(from).withDestination(destination)
                .withMessage(message);

        try {
            AWSCredentials credentials = getCredentials();
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient(credentials);
            Region REGION = Region.getRegion(Regions.US_EAST_1);
            client.setRegion(REGION);

            EmailResponse emailResponse = null;
            // Send the email.
            SendEmailResult response = client.sendEmail(request);
            System.out.println("Email sent!");
            System.out.println(response.getMessageId());
            emailResponse = new EmailResponse(200, response.getMessageId());
            return emailResponse;
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        return null;
    }
}