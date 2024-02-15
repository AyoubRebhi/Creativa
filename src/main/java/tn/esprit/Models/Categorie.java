package tn.esprit.Models;
import java.util.ArrayList;
import java.util.List;

public class Categorie {
    private int id_categorie;
    private String titre;
    private List<Projet> projets;

    public Categorie(int id_categorie, String titre) {
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.projets = new ArrayList<>();
    }


    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }
}
