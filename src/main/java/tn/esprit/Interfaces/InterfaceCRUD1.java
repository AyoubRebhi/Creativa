package tn.esprit.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCRUD1 <T>{
    void ajouter (T t);

    void modifier(T t);

    void supprimer(int id);

    void annulerCommande(int id) throws SQLException;
    void annulerLivraison(int id) throws SQLException;

    List<T> afficher() throws SQLException;
}
