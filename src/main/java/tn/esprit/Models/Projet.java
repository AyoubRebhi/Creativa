package tn.esprit.Models;

import java.util.Date;

public class Projet {
    private int id_projet;
    private String titre;
    private String description;
    private String media;
    private double prix;
    private int id_categorie;
    private Boolean isVisible =true;
    private Date createdAt = new Date();
    private Date updatedAt = createdAt;
    private int nombreJaime;


    public Projet(int id, String titre, String description, String media, double prix, int id_categorie) {
        this.id_projet = id;
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.prix = prix;
        this.id_categorie = id_categorie;
        //this.nombreJaime=nombreJaime;
        //this.isVisible = true;
    }

    public Projet(String titre, String description, String media, double prix, int id_categorie) {
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

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
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

    public Boolean getIsVisible() {
        return isVisible != null ? isVisible : false; // Retourner false si isVisible est null
    }


    public void setVisible(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getNombreJaime() {
        return nombreJaime;
    }

    public void setNombreJaime(int nombreJaime) {
        this.nombreJaime = nombreJaime;
    }
    @Override
    public String toString() {
        return "Projet{" +
                "id=" + id_projet +
                "titre="+titre +
                ", description='" + description + '\'' +
                ", media=" + media +
                ", prix=" + prix +
                ", visible="+ isVisible+
                "createdAt="+ createdAt+
                "updatedAt="+updatedAt+
                "Nombre des jaimes= "+ nombreJaime+
                '}';
    }
}
