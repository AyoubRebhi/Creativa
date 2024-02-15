package tn.esprit.Models;

import java.sql.Blob;

public class POST {
    private int Id,Topic_id;
    private String Editeur,Titre,Description;
            private String Media;


    public POST() {
    }

    public POST(int topic_id, String editeur, String titre, String description, String media) {
        Topic_id = topic_id;
        Editeur = editeur;
        Titre = titre;
        Description = description;
        Media = media;
    }

    public POST(int id, int topic_id, String editeur, String titre, String description, String media) {
        Id = id;
        Topic_id = topic_id;
        Editeur = editeur;
        Titre = titre;
        Description = description;
        Media = media;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getTopic_id() {
        return Topic_id;
    }

    public void setTopic_id(int topic_id) {
        Topic_id = topic_id;
    }

    public String getEditeur() {
        return Editeur;
    }

    public void setEditeur(String editeur) {
        Editeur = editeur;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMedia() {
        return Media;
    }

    public void setMedia(String media) {
        Media = media;
    }

    @Override
    public String toString() {
        return "POST{" +
                "Id=" + Id +
                ", Topic_id=" + Topic_id +
                ", Editeur='" + Editeur + '\'' +
                ", Titre='" + Titre + '\'' +
                ", Description='" + Description + '\'' +
                ", Media=" + Media +
                '}';
    }
}
