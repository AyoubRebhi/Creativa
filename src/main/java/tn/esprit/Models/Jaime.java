package tn.esprit.Models;
import java.util.Date;

public class Jaime {
    private int id_user;
    private int id_projet;
    private Date date = new Date();
    public Jaime(){

    }
    public Jaime(int id_projet, int id_user){
        this.id_projet=id_projet;
        this.id_user=id_user;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_projet() {
        return id_projet;
    }

    public void setId_projet(int id_projet) {
        this.id_projet = id_projet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Jaime{" +
                "id_user=" + id_user +
                ", id_projet=" + id_projet +
                ", date=" + date +
                '}';
    }
}
