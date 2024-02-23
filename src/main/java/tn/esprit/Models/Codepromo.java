package tn.esprit.Models;

public class Codepromo {
    private int code_promo;
    private String pourcentage;

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

    public Codepromo(int code_promo, String pourcentage) {
        this.code_promo = code_promo;
        this.pourcentage = pourcentage;
    }

    public Codepromo() {}

    @Override
    public String toString() {
        return "Codepromo{" +
                "code_promo=" + code_promo +
                ", pourcentage=" + pourcentage +
                '}';
    }
}
