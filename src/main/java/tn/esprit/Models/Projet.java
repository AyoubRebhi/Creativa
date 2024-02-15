package tn.esprit.Models;

import java.io.File;

public class Projet {
    private int id_projet;
    private String titre;
    private String description;
    private File media;
    private double prix;
    private int id_categorie;

    public Projet(int id, String titre, String description, File media, double prix, int id_categorie) {
        this.id_projet = id;
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.prix = prix;
        this.id_categorie = id_categorie;
    }

    public Projet(String titre, String description, File media, double prix, int id_categorie) {
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.prix = prix;
        this.id_categorie = id_categorie;

    }

    public int getCategorie() {
        return id_categorie;
    }

    public void setCategorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public Projet(){}
    public int getId() {
        return id_projet;
    }

    public void setId(int id) {
        this.id_projet = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id_projet +
                "titre="+titre +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", prix=" + prix +
                '}';
    }
}
