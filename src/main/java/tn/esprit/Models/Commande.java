package tn.esprit.Models;
import java.sql.Date;

public class Commande {
    //attributs
    private int id_cmd;
    private int id_user;
    private int id_projet;

    private Date date;

    private String mt_total;
    private int quantite;
    private Date date_livraison_estimee;
    private int code_promo;

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

    public String getMt_total() {
        return mt_total;
    }

    public int getQuantite() {
        return quantite;
    }

    public Date getDate_livraison_estimee() {
        return date_livraison_estimee;
    }

    public int getCode_promo() {
        return code_promo;
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

    public void setMt_total(String mt_total) {
        this.mt_total = mt_total;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setCode_promo(int code_promo) {
        this.code_promo = code_promo;
    }

    public void setDate_livraison_estimee(Date date_livraison_estimee) {
        this.date_livraison_estimee = date_livraison_estimee;


    }

    //constructeurs
    public Commande(int id_cmd, int id_user, int id_projet, Date date,String mt_total, int quantite,Date date_livraison_estimee,int code_promo) {
        this.id_cmd = id_cmd;
        this.id_user = id_user;
        this.id_projet = id_projet;
        this.date = date;
        this.mt_total = mt_total;
        this.quantite = quantite;
        this.date_livraison_estimee = date_livraison_estimee;
        this.code_promo = code_promo;
    }


    public Commande(int id_user, int id_projet, Date date, String mt_total, int quantite, Date date_livraison_estimee,int code_promo) {
        this.id_user = id_user;
        this.id_projet = id_projet;
        this.date = date;
        this.mt_total = mt_total;
        this.quantite = quantite;
        this.date_livraison_estimee = date_livraison_estimee;
        this.code_promo = code_promo;
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
                ", mt_total='" + mt_total + '\'' +
                ", quantite=" + quantite +
                ", date_livraison_estimee=" + date_livraison_estimee +
                ", code_promo=" + code_promo +
                '}';
    }


}
