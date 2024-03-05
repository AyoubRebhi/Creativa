package tn.esprit.maytest.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {

    public static MaConnexion instance;
    private final String URL="jdbc:mysql://localhost:3306/may";
    private final String USER="root";
    private final String PASSWORD="";

    private Connection conn;



    //On doit creer un constructeur pour initialiser cette variable
    private MaConnexion(){

        try {
            conn= DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connexion established");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //pour creer une seule connection à la base de donnée
    //On va utiliser la methode de penser (le patron de de conception) SINGLETON
    //Qui sert à l'unification de l'instanciation d'une classe
    public static MaConnexion getInstance(){
        //Si l'instance est nulle on va creer une instance
        if(instance == null){
            instance = new MaConnexion();
        }
        //Sinon la néme instance va etre ignorer et on va utiliser la 1ere Inst
        return instance;
    };

    public Connection getConn(){
        return conn;
    }

}