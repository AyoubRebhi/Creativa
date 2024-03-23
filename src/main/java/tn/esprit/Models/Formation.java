/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Formation {

    public Formation() {
    }
    private int id;
    private String titre;
    private String description;
    private String media;
    private int nb_places;
    private double prix;
    private List<Inscription> inscriptions = new ArrayList<>();


    public Formation(String titre, String description, String media, int nb_places, double prix) {
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.nb_places = nb_places;
        this.prix = prix;
    }

    public Formation(int id, String titre, String description, String media, int nb_places, double prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.media = media;
        this.nb_places = nb_places;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.titre);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.media);
        hash = 67 * hash + this.nb_places;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.prix) ^ (Double.doubleToLongBits(this.prix) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Formation other = (Formation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nb_places != other.nb_places) {
            return false;
        }
        if (Double.doubleToLongBits(this.prix) != Double.doubleToLongBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.media, other.media)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Formation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", media=" + media + ", nb_places=" + nb_places + ", prix=" + prix + '}';
    }
    
}
