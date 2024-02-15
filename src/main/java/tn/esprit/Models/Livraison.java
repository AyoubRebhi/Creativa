package tn.esprit.Models;

public class Livraison {
    //attributs
    private int id_liv;
    private int id_cmd;
    private int id_user;
    private String status;

    public enum Status {
        en_attente,
        en_cours,
        expédiée,
        livrée

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


    //constructeurs

    public Livraison(int id_liv, int id_cmd, int id_user, String status) {
        this.id_liv = id_liv;
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.status = status;
    }


    public Livraison(int id_cmd, int id_user, String status) {
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.status = status;
    }

    public Livraison(){}


}


