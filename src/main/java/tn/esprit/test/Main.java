package tn.esprit.test;

import tn.esprit.Models.Categorie;
import tn.esprit.Models.Jaime;
import tn.esprit.Models.Projet;
import tn.esprit.Services.CategorieServices;
import tn.esprit.Services.JaimeServices;
import tn.esprit.Services.ProjetServices;
import tn.esprit.Utils.MaConnexion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws SQLException {

        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println(connexion);
        ProjetServices ps = new ProjetServices();
        CategorieServices cs = new CategorieServices();

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
        /* List<Projet> projetsTrouves = projetServices.chercherParTitre("Malouf");
        for (Projet projet : projetsTrouves) {
            System.out.println("ID: " + projet.getId());
            System.out.println("Titre: " + projet.getTitre());
            System.out.println("Description: " + projet.getDescription());
            System.out.println("Prix: " + projet.getPrix());
            System.out.println("Catégorie: " + projet.getCategorie());
            System.out.println("-------------------------");
        } */
        /*CategorieServices cs = new CategorieServices();
        Categorie c = new Categorie("Theatre");
        cs.ajouter(c); */

        /*List<Projet> projets=ps.rechercheParCategorie(1);
        for (Projet projet : projets) {
            System.out.println("ID: " + projet.getId());
            System.out.println("Titre: " + projet.getTitre());
            System.out.println("Description: " + projet.getDescription());
            System.out.println("Prix: " + projet.getPrix());
            System.out.println("Catégorie: " + projet.getCategorie());
            System.out.println("-------------------------");
        }*/
        /*int idCategorie = 3;
        Map<String, Integer> nb = cs.calculerNbProjets(idCategorie);
        for (Map.Entry<String, Integer> entry : nb.entrySet()) {
            String categorieTitre = entry.getKey();
            int nbProjets = entry.getValue();
            System.out.println("- Catégorie : " + categorieTitre + ", Nombre de projets : " + nbProjets);
        }*/
        List<Categorie> categories = cs.afficher();
        /*for(Categorie c : categories){
            System.out.println("ID : "+ c.getId_categorie());
            System.out.println("Titre : "+ c.getTitre());
        }*/
        //ps.modifierVisibilite(3,true);
        /*Jaime jaime = new Jaime();
        jaime.setId_user(1);
        jaime.setId_projet(1);

        // Créer un objet JaimeServices
        JaimeServices jaimeServices = new JaimeServices();

        // Appeler la méthode insererJaime pour insérer le "like" dans la base de données
        jaimeServices.insererJaime(jaime);*/
        List<Projet> projets = ps.afficher();
        for (Projet projet : projets) {
            ps.calculerNombreJaimePourProjet(projet);
            System.out.println("ID: " + projet.getId());
            System.out.println("Titre: " + projet.getTitre());
            System.out.println("jaimes " + projet.getNombreJaime());
        }

    }
}