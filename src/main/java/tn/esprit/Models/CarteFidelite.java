package tn.esprit.Models;

public class CarteFidelite {

    private int idCarteFidelite;
    private int points;

    public CarteFidelite() {
    }

    private User user;

    // Constructeur sans idCarteFidelite
    public CarteFidelite(int points, User user) {
        this.points = points;
        this.user = user;
    }

    // Getters et Setters
    public int getIdCarteFidelite() {
        return idCarteFidelite;
    }

    public void setIdCarteFidelite(int idCarteFidelite) {
        this.idCarteFidelite = idCarteFidelite;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
