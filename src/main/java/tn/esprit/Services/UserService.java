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

public class  UserService implements InterfaceCRUD<User> {

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
    // ajouter avec mail de confirmation
    public void ajouter4(User user) {
        String req = "INSERT INTO user (last_name, first_name, username, password, role, biography, address, ImgPath,email,numTel) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";

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
            preparedStatement.setString(9, user.getEmail());
            preparedStatement.setInt(10, user.getNumTel());

            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("user Added Successfully!");

            // Appeler la fonction envoyerEmailConfirmation
            emailUtil.envoyerEmailConfirmation(user);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUtilisateurRole(String identifiant) {
        String req = "SELECT role FROM user WHERE (username=? OR email=?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, identifiant);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer le mot de passe haché de la base de données

                    // Vérifier si le mot de passe fourni correspond au mot de passe haché
                        // Retourner le rôle si la vérification du mot de passe est réussie
                        return resultSet.getString("role");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retourner une valeur par défaut en cas d'échec de l'authentification
        return null;
    }


    public boolean emailExists(String email) {
        String req = "SELECT * FROM user WHERE email=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // true if email exists, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check if a username already exists in the database
    public boolean usernameExists(String username) {
        String req = "SELECT * FROM user WHERE username=?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // true if username exists, false otherwise
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    //fonction modifier
    public void modifier(User user) {
        String req = "UPDATE user SET last_name = ?, first_name = ?, username = ?, password = ?, role = ?, biography = ?, address = ?, profile_image_path = ?,email=? WHERE id_user= ?";

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
            preparedStatement.setString(9,user.getEmail());
            preparedStatement.setInt(10, user.getId());

            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("modified");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    //fonction supprimer
    public void supprimer(int id) {
        String DELETE_USER_SQL = "DELETE FROM user WHERE id_user = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, id);

            // Execute the statement
            preparedStatement.executeUpdate();
            System.out.println("supprimé avec succéesS");

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
                utilisateur.setNumTel(Integer.parseInt(resultSet.getString("numTel")));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gestion des exceptions SQL
        }

        // Retourne la liste complète d'utilisateurs
        return utilisateurs;
    }

    public boolean verifierUtilisateur(String identifiant, String motDePasse) {
        String req = "SELECT * FROM user WHERE (username=? OR email=?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, identifiant);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer le mot de passe haché de la base de données
                    String hashedPasswordFromDB = resultSet.getString("password");

                    // Vérifier si le mot de passe fourni correspond au mot de passe haché
                    return BCrypt.checkpw(motDePasse, hashedPasswordFromDB);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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


    // Fonction pour récupérer un utilisateur par son ID
    public User getById(int id) {
        User utilisateur = null;
        String req = "SELECT * FROM user WHERE id_user = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            // Définir l'ID de l'utilisateur pour la requête préparée
            preparedStatement.setInt(1, id);

            // Exécuter la requête
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    utilisateur = new User();
                    utilisateur.setId(resultSet.getInt("id_user"));
                    utilisateur.setLastName(resultSet.getString("last_name"));
                    utilisateur.setFirstName(resultSet.getString("first_name"));
                    utilisateur.setUsername(resultSet.getString("username"));
                    utilisateur.setPassword(resultSet.getString("password"));
                    utilisateur.setRole(Role.valueOf(resultSet.getString("role")));
                    utilisateur.setBiography(resultSet.getString("biography"));
                    utilisateur.setAddress(resultSet.getString("address"));
                    utilisateur.setProfileImagePath(resultSet.getString("ImgPath"));
                    utilisateur.setEmail(resultSet.getString("email"));
                    utilisateur.setNumTel(resultSet.getInt("numTel"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilisateur;
    }


    public int getIdUtilisateur (String username, String password) throws SQLException{
        int k=0;
        String requete = "select id_user  from user where username=? and password=?";

        PreparedStatement pst = cnx
                .prepareStatement(requete);
        pst.setString(1,username );
        pst.setString(2,password );
        ResultSet rs=pst.executeQuery();
        while(rs.next()){
            k=   rs.getInt("id_utilisateur");}

        return k;
    }



    public int getUtilisateurid(String identifiant, String motDePasse) {
        String req = "SELECT id_user, password FROM user WHERE (username=? OR email=?)";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, identifiant);
            preparedStatement.setString(2, identifiant);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer le mot de passe haché de la base de données
                    String hashedPasswordFromDB = resultSet.getString("password");

                    // Vérifier si le mot de passe fourni correspond au mot de passe haché
                    if (BCrypt.checkpw(motDePasse, hashedPasswordFromDB)) {
                        // Retourner l'ID si la vérification du mot de passe est réussie
                        return resultSet.getInt("id_user");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retourner une valeur par défaut en cas d'échec de l'authentification
        return -1;
    }



    public boolean resetPassword(String userEmail, String newPassword) {
        // Implement logic to update the user's password in the database
        String updateQuery = "UPDATE user SET password = ? WHERE email = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(updateQuery)) {
            // Hash the new password using BCrypt
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setString(2, userEmail);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean resetPasswordparid(int idUser, String newPassword) {
        // Implement logic to update the user's password in the database
        String updateQuery = "UPDATE user SET password = ? WHERE id_user = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(updateQuery)) {
            // Hash the new password using BCrypt
            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            preparedStatement.setString(1, hashedPassword);
            preparedStatement.setInt(2, idUser);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}