package tn.esprit;

import tn.esprit.Utils.MaConnexion;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        MaConnexion connexion = MaConnexion.getInstance();
        System.out.println(connexion);

    }
}