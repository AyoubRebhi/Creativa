package tn.esprit.Models;

public class TOPIC {
int id;
int catego_ID;
String Nom;
String Subject;

    public TOPIC() {
    }

    public TOPIC(int catego_ID, String nom, String subject) {
        this.catego_ID = catego_ID;
        Nom = nom;
        Subject = subject;
    }

    public TOPIC(int id, int catego_ID, String nom, String subject) {
        this.id = id;
        this.catego_ID = catego_ID;
        Nom = nom;
        Subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCatego_ID() {
        return catego_ID;
    }

    public void setCatego_ID(int catego_ID) {
        this.catego_ID = catego_ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String toString() {
        return "TOPIC{" +
                "id=" + id +
                ", catego_ID=" + catego_ID +
                ", Nom='" + Nom + '\'' +
                ", Subject='" + Subject + '\'' +
                '}';
    }
}
