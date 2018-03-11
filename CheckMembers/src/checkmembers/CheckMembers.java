package checkmembers;

import java.io.*;
import java.net.*;
import java.util.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class CheckMembers {

    // called in step 1
    public static JSONArray getMembers(String urlString) throws Exception {
        
        URL url = new URL(urlString); 
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader sr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader in = new BufferedReader(sr);
        
        JSONParser jsonParser = new JSONParser();
        JSONArray members = (JSONArray) jsonParser.parse(in);
        return members;
    }
    
    // called in step 2
    public static JSONObject getMembersInfo(String urlString) throws Exception {
        
        URL url = new URL(urlString); 
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader sr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader in = new BufferedReader(sr);
        
        JSONParser jsonParser = new JSONParser();
        JSONObject members = (JSONObject) jsonParser.parse(in);
        return members;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner reader = new Scanner(System.in); 
        System.out.println("Input the organization name please: ");
        String org_name = reader.nextLine();
        String org_url = "https://api.github.com/orgs/" + org_name + "/members";
        System.out.println("Input the admin email please: ");
        String admin_email = reader.nextLine();
        reader.close();
        
        // testing strings
        //String url = "https://api.github.com/orgs/byuitechops/members";
        //String url = "https://api.github.com/orgs/byutest/members";
        
        // STEP 1
        // get the organization members
        JSONArray members = getMembers(org_url);
        // and get their logins for using in the step 2
        List<String> logins = new ArrayList<>();
        for (Object member : members){
            String[] items = member.toString().split(",");
            for (String item : items){
                CharSequence s = "login";
                if (item.contains(s)){
                    String[] temp = item.split(":");
                    logins.add(temp[1]);
                }  
            }
        }
        System.out.println("------------------------------------------");
        
        // STEP 2
        // get detailed info for each member
        Map<String, String> members_map = new TreeMap<>();
        for (String login : logins){
            login = login.substring(1, login.length() - 1);
            System.out.println("Member login: " + login);
            String member_url = "https://api.github.com/users/" + login;
            JSONObject member_info = getMembersInfo(member_url);
            String member_info_str = member_info.toString();
            // parse the member info to get her name and email
            String name = "", email = "";
            String[] mis_items = member_info_str.split(",");
            for (String mis_item : mis_items){
                CharSequence s1 = "name";
                if (mis_item.contains(s1)){
                    String[] temp1 = mis_item.split(":");
                        name = temp1[1];
                    System.out.println("Member name: " + name);
                }
                CharSequence s2 = "email";
                if (mis_item.contains(s2)){
                    String[] temp2 = mis_item.split(":");
                        email = temp2[1];
                    System.out.println("Member email: " + email);
                }
            }
            // for all members' names and emails
            members_map.put(name, email);
        }
        System.out.println("-------------------------");
        
        // STEP 3
        // send emails to the members with no names
        EmailSender email_sender = new EmailSender();
        for (Map.Entry<String, String> entry : members_map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null){
                email_sender.sendEmail(value, admin_email);
            }
        }
    }
    
}
