package tn.esprit.Models;

import java.util.Date;

public class Codepromo {
    private int id;
    private int code_promo;
    private String pourcentage;
    private Date date_expiration;;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode_promo() {
        return code_promo;
    }

    public void setCode_promo(int code_promo) {
        this.code_promo = code_promo;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Codepromo(int id, int code_promo, String pourcentage,Date date,Date date_expiration) {
        this.id = id;
        this.code_promo = code_promo;
        this.pourcentage = pourcentage;
        this.date = date;
        this.date_expiration = date_expiration;

    }


    public Codepromo() {}


    @Override
    public String toString() {
        return "Codepromo{" +
                "id=" + id +
                ", code_promo=" + code_promo +
                ", pourcentage='" + pourcentage + '\'' +
                ", date_expiration=" + date_expiration +
                ", date=" + date +
                '}';
    }
}
