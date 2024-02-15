package tn.esprit.Models;
import java.sql.Date;

public class Commande {
    //attributs
    private int id_cmd;
    private int id_user;
    private int id_projet;

    private Date date;

    //getters
    public int getId_cmd() {
        return id_cmd;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_projet() {
        return id_projet;
    }

    public Date getDate() {
        return date;
    }

    //setters

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //constructeurs
    public Commande(int id_cmd, int id_user, int id_projet, Date date) {
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.id_projet = id_projet;
        this.date = date;
    }

    public Commande(int id_user, int id_projet, Date date) {
        this.id_user = id_user;
        this.id_projet = id_projet;
        this.date = date;
    }

    public Commande(){}

    //tostring
    @Override
    public String toString() {
        return "Commande{" +
                "id_cmd=" + id_cmd +
                ", id_user=" + id_user +
                ", id_projet=" + id_projet +
                ", date=" + date +
                '}';
    }
}
