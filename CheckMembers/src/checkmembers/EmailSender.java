package checkmembers;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author Yura
 */
public class EmailSender {
    public void sendMessage(String to, String from, String login){
        
        Properties props = new Properties();
        props.put("mail.smtp.com" , "smtp.gmail.com");
        Session session  = Session.getDefaultInstance( props , null);
   
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
            msg.setSubject("Github name missing!");
            msg.setText("Dear " + login + " ,\nPlease set up your name in your github.");
        }  
        catch(Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }
}
