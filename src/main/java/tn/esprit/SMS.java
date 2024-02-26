package tn.esprit;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    // Remplacez ces valeurs par vos propres informations d'identification Twilio
    public static final String ACCOUNT_SID = "AC54e9aa0a574aae8b664854b28ca0cab2";
    public static final String AUTH_TOKEN = "dcb6fe67dd51d548e3a9df5b83a5fc55";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("+21653125536"), // Numéro du destinataire
                        new PhoneNumber("+19732873262"), // Votre numéro Twilio
                        "Nous sommes ravis de vous informer que votre commande est en route vers votre adresse. Notre équipe de livraison est en chemin pour vous apporter vos articles dans les meilleurs délais.\n" +
                                "\n" +
                                "Nous vous remercions pour votre confiance et votre patience. N'hésitez pas à nous contacter si vous avez des questions ou des préoccupations.")
                .create();
        System.out.println(message.getSid());
    }
}
