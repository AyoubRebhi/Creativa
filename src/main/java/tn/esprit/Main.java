package tn.esprit;

import tn.esprit.Models.CarteFidelite;
import tn.esprit.Models.Role;
import tn.esprit.Models.User;
import tn.esprit.Services.CarteFideliteService;
import tn.esprit.Services.UserService;
import tn.esprit.Utils.MaConnexion;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

     // Créez un objet User pour les tests
        User testUser = new User("ayachi", "zeinedddb", "happppp588", "password123",
               Role.ADMIN, "A short bio", "123 Main St", "/path/to/image.jpg", "hasssssmrouni.hajer@esprit.tn");
//
//        // Créez un service utilisateur
       UserService userService = new UserService();
//
//        // Ajoutez l'utilisateur à la base de données
        userService.ajouter4(testUser);

         //Affichez la liste des utilisateurs pour vérifier l'ajout
        List<User> users = userService.afficher();
        System.out.println("Liste des utilisateurs après ajout :");
        for (User user : users) {
            System.out.println(user);
        }

        // Testez l'authentification avec un nom d'utilisateur ou une adresse e-mail et un mot de passe
        String usernameOrEmail = "hasssssmrouni.hajer@esprit.tn"; // Remplacez par le nom d'utilisateur ou l'adresse e-mail à tester
        String password = "passwor23"; // Remplacez par le mot de passe à tester

        if (userService.verifierUtilisateur(usernameOrEmail, password)) {
            System.out.println("Authentification réussie pour " + usernameOrEmail);
        } else {
            System.out.println("Échec de l'authentification pour " + usernameOrEmail);
        }
    }




    }



