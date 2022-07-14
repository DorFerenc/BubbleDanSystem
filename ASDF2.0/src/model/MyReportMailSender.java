package model;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MyReportMailSender
{
	public final String SENDFROM = "\n\n\nSend from ASDF";

	//SETUP MAIL SERVER PROPERTIES
	//DRAFT AN EMAIL
	//SEND EMAIL
	Session newSession = null;
	MimeMessage mimeMessage = null;
	
	public void initSendMail(String[] emailReceipients, String emailSubject, String emailText) throws AddressException, MessagingException, IOException
	{
		MyReportMailSender mail = new MyReportMailSender();
		System.out.println("setting up");
		mail.setupServerProperties();
		System.out.println("drafting up");
		mail.draftEmail(emailReceipients, emailSubject, emailText+ SENDFROM);
		System.out.println("sending...");
		mail.sendEmail();
	}

	private void sendEmail() throws MessagingException {
		String fromUser = "asfd12bubble@gmail.com";  //Enter sender email id
		String fromUserPassword = "asdf12ASDF";  //Enter sender gmail password , this will be authenticated by gmail smtp server
		String emailHost = "smtp.gmail.com";
		Transport transport = newSession.getTransport("smtp");
		transport.connect(emailHost, fromUser, fromUserPassword);
		transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		transport.close();
		System.out.println("Email successfully sent!!!");
	}

	private MimeMessage draftEmail(String[] emailReceipients, String emailSubject, String emailText) throws AddressException, MessagingException, IOException {
		//String[] emailReceipients = {"bezabuilders@gmail.com","asdf12bubble@gmail.com"};  //Enter list of email recepients
		//String[] emailReceipients = {"bezabuilders@gmail.com"/*, "alonasshlomi@gmail.com"*/};
		//String emailSubject = "Test Mail";
		//String emailBody = "Test Body of my email";
		mimeMessage = new MimeMessage(newSession);

		for (int i =0 ;i<emailReceipients.length;i++)
		{
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
		}
		mimeMessage.setSubject(emailSubject);

		// CREATE MIMEMESSAGE 
		// CREATE MESSAGE BODY PARTS 
		// CREATE MESSAGE MULTIPART 
		// ADD MESSAGE BODY PARTS ----> MULTIPART 
		// FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object 


		//		 MimeBodyPart bodyPart = new MimeBodyPart();
		//		 bodyPart.setContent(emailBody,"html/text");
		//		 MimeMultipart multiPart = new MimeMultipart();
		//		 multiPart.addBodyPart(bodyPart);
		//		 mimeMessage.setContent(multiPart);
		mimeMessage.setSubject(emailSubject);
		mimeMessage.setText(emailText);
		return mimeMessage;
	}

	private void setupServerProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		newSession = Session.getDefaultInstance(properties,null);
	}

}