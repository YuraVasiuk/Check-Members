package checkmembers;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.*;

public class CheckMembers {

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
    
    /*
    public static JSONObject getMembers(String urlString) throws Exception {
        
        URL url = new URL(urlString); 
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  
        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
        InputStreamReader sr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader in = new BufferedReader(sr);
        
        JSONParser jsonParser = new JSONParser();
        JSONObject members = (JSONObject) jsonParser.parse(in);
        //JSONArray members = (JSONArray) jsonParser.parse(in);
        return members;
    }
    */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String url = "https://api.github.com/orgs/byuitechops/members";
        //String url = "https://api.github.com/orgs/byutest/members";
        
        // STEP 1
        // get the organization members
        JSONArray members = getMembers(url);
        // and get their logins for using in the step 2
        List<String> logins = new ArrayList<>();
        int members_counter = 0;
        for (Object member : members){
            members_counter++;
            System.out.println("Member #: " + members_counter);
            String[] items = member.toString().split(",");
            for (String item : items){
                CharSequence s = "login";
                if (item.contains(s)){
                    String[] temp = item.toString().split(":");
                    logins.add(temp[1]);
                    System.out.println("Member login: " + temp[1]);
                }  
            }
        }
        System.out.println("All organization members have been listed.");
        
        // STEP 2
        // get detailed info for each member
        for (String login : logins){
            System.out.println(login);
        }
        
        /*
        String url = "https://api.github.com/users/meeple142";
        JSONObject member = getMembers(url);
        System.out.println(member);
        */
    }
    
}
