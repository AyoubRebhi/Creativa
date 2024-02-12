package tn.esprit.Utils;


import tn.esprit.Models.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

// Dans le package 'util'
public class emailUtil {

    public static void envoyerEmailConfirmation(User user) {
        // Remplacez ces valeurs par les paramètres appropriés de votre serveur SMTP
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "hhajer09@gmail.com";
        String password = "vhxo yiyf ajcb ktjm";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Créer une session de messagerie avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer le message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Confirmation d'inscription");
            message.setText("Bonjour " + user.getFirstName() + ",\n\nMerci de vous être inscrit sur notre plateforme.");

            // Envoyer le message
            Transport.send(message);

            System.out.println("E-mail de confirmation envoyé avec succès.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de l'envoi de l'e-mail de confirmation.");
        }
    }

}

