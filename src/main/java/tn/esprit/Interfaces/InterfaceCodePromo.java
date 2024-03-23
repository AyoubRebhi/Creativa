package tn.esprit.Interfaces;

import tn.esprit.Models.Codepromo;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceCodePromo <T>{
    void ajouter (T t);

    Codepromo rechercher(T t);

    List<T> afficher() throws SQLException;


}
