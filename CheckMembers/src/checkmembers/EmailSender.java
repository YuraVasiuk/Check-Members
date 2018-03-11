package checkmembers;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    
    public void sendEmail(String to, String from){
        
        Properties props = new Properties();
        props.put("mail.smtp.com" , "smtp.gmail.com");
        Session session  = Session.getDefaultInstance( props , null);
   
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO , new InternetAddress(to));
            msg.setSubject("Github name missing!");
            msg.setText("Dear " + to + " ,\nPlease set up your name in your github.");
        }  
        catch(Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
        
    }
}
