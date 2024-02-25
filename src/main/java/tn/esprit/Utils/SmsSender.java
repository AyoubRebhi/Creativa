/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Utils;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsSender {
  
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC24a26b25f1ce71aabc417c464e13a162";
    public static final String AUTH_TOKEN =
            "7de2be7f4a1ad3b1c0682c107e19ec67";


    public void send(String s,String x){
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      String y="+216"+x;
        Message message = Message 
                .creator(new PhoneNumber(y), // to
                        new PhoneNumber("+21625513838"), // from
                       ""+s)
                .create();
  System.out.println("heeey");
        System.out.println(message.getSid());
    }
}
    

