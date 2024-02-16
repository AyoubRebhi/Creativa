package tn.esprit;

import tn.esprit.Models.Projet;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Utils.MaConnexion;

import java.sql.SQLException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {

        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println(connexion);
        ProjetServices projetServices = new ProjetServices();


            /*List<Projet> projets = projetServices.afficher();
            for (Projet projet : projets) {
                System.out.println("ID: " + projet.getId());
                System.out.println("Titre: " + projet.getTitre());
                System.out.println("Description: " + projet.getDescription());
                System.out.println("Prix: " + projet.getPrix());
                System.out.println("Catégorie: " + projet.getCategorie());
                System.out.println("-------------------------");
            }*/
       /* Projet projet =projetServices.afficherProjetParId(6);
        if (projet != null) {
            System.out.println("Informations du projet :");
            System.out.println("ID : " + projet.getId());
            System.out.println("Titre : " + projet.getTitre());
            System.out.println("Description : " + projet.getDescription());
            System.out.println("Prix : " + projet.getPrix());
            System.out.println("Catégorie : " + projet.getCategorie());
        } else {
            System.out.println("Aucun projet trouvé avec cet ID.");
        } */
        List<Projet> projetsTrouves = projetServices.chercherParTitre("Malouf");
        for (Projet projet : projetsTrouves) {
            System.out.println("ID: " + projet.getId());
            System.out.println("Titre: " + projet.getTitre());
            System.out.println("Description: " + projet.getDescription());
            System.out.println("Prix: " + projet.getPrix());
            System.out.println("Catégorie: " + projet.getCategorie());
            System.out.println("-------------------------");
        }


    }
}