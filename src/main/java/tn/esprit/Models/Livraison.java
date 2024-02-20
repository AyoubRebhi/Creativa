package tn.esprit.Models;

public class Livraison {
    //attributs
    private int id_liv;
    private int id_cmd;
    private int id_user;
    private String status;
    private String adresse;
    private String frais_liv;

    private String moyen_livraison;
    public enum Status {

        en_cours,
        livrée

    }

    public enum moyen_livraison {

        standard,
        express

    }

    //getters


    public int getId_liv() {
        return id_liv;
    }

    public int getId_cmd() {
        return id_cmd;
    }

    public int getId_user() {
        return id_user;
    }

    public String getStatus() {
        return status;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getFrais_liv() {
        return frais_liv;
    }

    public String getMoyen_livraison() {
        return moyen_livraison;
    }

    //setters


    public void setId_liv(int id_liv) {
        this.id_liv = id_liv;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setFrais_liv(String frais_liv) {
        this.frais_liv = frais_liv;
    }

    public void setMoyen_livraison(String moyen_livraison) {
        this.moyen_livraison = moyen_livraison;
    }

//constructeurs


    public Livraison(int id_liv, int id_cmd, int id_user, String status,String adresse,String frais_liv,String moyen_livraison) {
        this.id_liv = id_liv;
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.status = status;
        this.adresse = adresse;
        this.frais_liv=frais_liv;
        this.moyen_livraison = moyen_livraison;
    }



    public Livraison(int id_cmd, int id_user, String status, String adresse, String frais_liv,String moyen_livraison) {
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.status = status;
        this.adresse = adresse;
        this.frais_liv=frais_liv;
        this.moyen_livraison = moyen_livraison;
    }

    public Livraison(){}

//tostring

    @Override
    public String toString() {
        return "Livraison{" +
                "id_liv=" + id_liv +
                ", id_cmd=" + id_cmd +
                ", id_user=" + id_user +
                ", status='" + status + '\'' +
                ", adresse='" + adresse + '\'' +
                ", frais_liv='" + frais_liv + '\'' +
                ", moyen_livraison='" + moyen_livraison + '\'' +
                '}';
    }
}


