package tn.esprit.Interfaces;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCRUD <T>{

    void ajouter (T t);

    void modifier(T t);

    void supprimer(int id);

    List<T> afficher() throws SQLException;

}