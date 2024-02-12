package tn.esprit.Models;

import java.io.File;

public class Projet {
    private int id_projet;
    private String titre;
    private String description;
    private File media;
    private double prix;

    public Projet(int id,String titre, String description, File media, double prix) {
        this.id_projet = id;
        this.description = description;
        this.media = media;
        this.prix = prix;
        this.titre=titre;
    }

    public Projet(String titre, String description, File media, double prix) {
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.prix = prix;
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
