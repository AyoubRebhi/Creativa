package tn.esprit;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Properties;

import tn.esprit.Models.Commande;

import tn.esprit.Models.Livraison;
import tn.esprit.Services.ServiceCommande;
import tn.esprit.Services.ServiceLivraison;
import tn.esprit.Utils.MaConnexion;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println(connexion);

        //ajouter commande
        /*ServiceCommande serviceCommande = new ServiceCommande();

        Commande c = new Commande(1 , 2 , new Date(123,11,25));

        serviceCommande.ajouter(c); // Appel de la méthode sur l'instance serviceCommande
    }*/

        //supprimer commande

       /* ServiceCommande serviceCommande = new ServiceCommande();
        serviceCommande.supprimer(2);}*/


        //modifier commande
        /*ServiceCommande serviceCommande = new ServiceCommande();
        // Création d'une instance de Commande à modifier
        Commande c = new Commande();
        c.setId_cmd(1); // ID de la commande que je veux modifier
        c.setId_user(1); // 'ID de l'utilisateur
        c.setId_projet(3); // ID du projet

        // Création de la date "2023-02-10" en utilisant java.sql.Date
        Date dateSpecifiee = Date.valueOf("2023-02-10");
        //affecter date
        c.setDate(dateSpecifiee);
        serviceCommande.modifier(c);
*/


        //ajouter livraison

           /* ServiceLivraison serviceLivraison = new ServiceLivraison();
            Livraison l = new Livraison(3, 1, "livrée");
            serviceLivraison.ajouter(l);
        }*/


        //modifier livraison

        /*ServiceLivraison serviceLivraison = new ServiceLivraison();
        Livraison l = new Livraison(2,3, 1, "en_attente");
        serviceLivraison.modifier(l);
    }*/

        //supprimer livraison
        /*ServiceLivraison serviceLivraison = new ServiceLivraison();
        serviceLivraison.supprimer(1);*/




        //email

                // Paramètres SMTP
                /*String host = "smtp.gmail.com";
                String port = "587"; // Port du serveur SMTP
                String username = "zeineb2000000@gmail.com";
                String password = "mbfa ibjl qutl bryv";

                // Paramètres de l'email
                String recipient = "zeinebayachi12@gmail.com";
                String subject = "État de la livraison";
                String content = "Cher utilisateur,\n\nNous sommes heureux de vous informer que votre colis a été livré avec succès à l'adresse indiquée.\n" +
                        "\n" +
                        "Cordialement,";

                // Configurer les propriétés pour la session
                Properties properties = new Properties();
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.port", port);
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");

                // Créer une session avec authentification
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("zeineb2000000@gmail.com", "mbfa ibjl qutl bryv");
                    }
                });

                try {
                    // Créer un message
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
                    message.setSubject(subject);
                    message.setText(content);

                    // Afficher un message de débogage
                    System.out.println("Message créé avec succès.");

                    // Envoyer le message
                    Transport.send(message);
                    System.out.println("Email envoyé avec succès.");
                } catch (MessagingException e) {
                    e.printStackTrace();
                    System.out.println("Erreur lors de l'envoi de l'email: " + e.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Une erreur s'est produite: " + ex.getMessage());
                }*/
            }

}