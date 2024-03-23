package tn.esprit.Models;

public class TOPIC {
int id;
String catego_ID;
String Nom;
String Subject,Image;

    public TOPIC() {
    }

    public TOPIC(String catego_ID, String nom, String subject,String image) {
        this.catego_ID = catego_ID;
        Nom = nom;
        Subject = subject;
Image=image;
    }

    public TOPIC(int id, String catego_ID, String nom, String subject,String image) {
        this.id = id;
        this.catego_ID = catego_ID;
        Nom = nom;
        Subject = subject;
        Image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatego_ID() {
        return catego_ID;
    }

    public void setCatego_ID(String catego_ID) {
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
    public String getImage(){return Image;}
public void setImage(String image){Image=image;}
    @Override
    public String toString() {
        return
               "                     "+Nom + "    " +Subject  ;

    }
}
