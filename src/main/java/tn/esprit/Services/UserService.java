package tn.esprit.Services;
import org.mindrot.jbcrypt.BCrypt;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Role;
import tn.esprit.Models.User;
import tn.esprit.Utils.MaConnexion;
import tn.esprit.Utils.emailUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements InterfaceCRUD<User> {

    private Connection connection;


    Connection cnx = MaConnexion.getInstance().getConn();

    //fonction ajouter
    @Override
    public void ajouter(User user) {
        String req = "INSERT INTO user (last_name, first_name, username, password, role, biography, address, ImgPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            // Set values for the prepared statement
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getBiography());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getProfileImagePath());

            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("user Added Successfully!");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //ajouter avec mail de confirmation
//    public void ajouter4(User user) {
//        String req = "INSERT INTO user (last_name, first_name, username, password, role, biography, address, ImgPath,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
//
//        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
//            // Set values for the prepared statement
//            preparedStatement.setString(1, user.getLastName());
//            preparedStatement.setString(2, user.getFirstName());
//            preparedStatement.setString(3, user.getUsername());
//            preparedStatement.setString(4, user.getPassword());
//            preparedStatement.setString(5, user.getRole().name());
//            preparedStatement.setString(6, user.getBiography());
//            preparedStatement.setString(7, user.getAddress());
//            preparedStatement.setString(8, user.getProfileImagePath());
//            preparedStatement.setString(9, user.getEmail());
//
//            // Execute the statement
//            preparedStatement.executeUpdate();
//            System.out.println("user Added Successfully!");
//
//            // Appeler la fonction envoyerEmailConfirmation
//            emailUtil.envoyerEmailConfirmation(user);
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }

//ajouter avec hashage de mot passe et mail envoyé
    public void ajouter2(User user) {
        String req = "INSERT INTO user (last_name, first_name, username, password, role, biography, address, profile_image_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            // Set values for the prepared statement
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getUsername());

            // Hash the password using BCrypt
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            preparedStatement.setString(4, hashedPassword);

            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getBiography());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getProfileImagePath());

            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("User Added Successfully!");
// Appeler la fonction envoyerEmailConfirmation
            emailUtil.envoyerEmailConfirmation(user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    //fonction modifier
    public void modifier(User user) {
        String req = "UPDATE user SET last_name = ?, first_name = ?, username = ?, password = ?, role = ?, biography = ?, address = ?, profile_image_path = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            // Set values for the prepared statement
            preparedStatement.setString(1, user.getLastName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.setString(6, user.getBiography());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setString(8, user.getProfileImagePath());
            preparedStatement.setInt(9, user.getId());

            // Execute the statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //fonction supprimer
    public void supprimer(int id) {
        String DELETE_USER_SQL = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, id);

            // Execute the statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //afficher
    public List<User> afficher() {
        List<User> utilisateurs = new ArrayList<>();
        String req = "SELECT * FROM user";

        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {

            while (resultSet.next()) {
                // Crée un nouvel objet User pour chaque ligne dans le résultat de la requête
                User utilisateur = new User();

                // Récupère les valeurs des colonnes dans le résultat de la requête et les attribue à l'objet User
                utilisateur.setId(resultSet.getInt("id_user"));
                utilisateur.setLastName(resultSet.getString("last_name"));
                utilisateur.setFirstName(resultSet.getString("first_name"));
                utilisateur.setUsername(resultSet.getString("username"));
                utilisateur.setPassword(resultSet.getString("password"));

                // Utilisation d'une méthode pour convertir la chaîne en enum
                utilisateur.setRole(Role.valueOf(resultSet.getString("role")));

                utilisateur.setBiography(resultSet.getString("biography"));
                utilisateur.setAddress(resultSet.getString("address"));
                utilisateur.setProfileImagePath(resultSet.getString("ImgPath"));

                // Ajoute l'objet User à la liste
                utilisateur.setEmail(resultSet.getString("email"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des exceptions SQL
        }

        // Retourne la liste complète d'utilisateurs
        return utilisateurs;
    }

    public boolean verifierUtilisateur(String identifiant, String motDePasse) {
        String req = "SELECT * FROM user WHERE (username=? OR email=?) AND password=?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, identifiant);
            preparedStatement.setString(3, motDePasse);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                System.out.println("donnes valide");

                return resultSet.next(); // Si une ligne est retournée, l'utilisateur existe

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("donnes invalide");
        }
        return false;

}
    public int nombreTotalUtilisateurs() {
        String req = "SELECT COUNT(*) FROM user";

        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

}