package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.CarteFidelite;
import tn.esprit.Models.User;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarteFideliteService implements InterfaceCRUD<CarteFidelite> {
    private Connection connection;


    Connection cnx = MaConnexion.getInstance().getConn();

    @Override

    public void ajouter(CarteFidelite carteFidelite) {
        String sql = "INSERT INTO cartes_fidelite (id_user, points) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, carteFidelite.getUser().getId());
            preparedStatement.setInt(2, carteFidelite.getPoints());

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    carteFidelite.setIdCarteFidelite(generatedKeys.getInt(1));
                    System.out.println("carte Added Successfully!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void modifier(CarteFidelite carteFidelite) {
        CarteFideliteService h = new CarteFideliteService();
        String sql = "UPDATE cartes_fidelite SET points = ? WHERE id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, carteFidelite.getPoints());
            preparedStatement.setInt(2, carteFidelite.getIdCarteFidelite());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM cartes_fidelite WHERE id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("supprimé avec succéesS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CarteFidelite> afficher() {
        List<CarteFidelite> listeCartesFidelite = new ArrayList<>();
        String sql = "SELECT * FROM cartes_fidelite";
        try (Statement statement = cnx.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                CarteFidelite carteFidelite = new CarteFidelite();
                carteFidelite.setIdCarteFidelite(resultSet.getInt("id_carte"));
                carteFidelite.setPoints(resultSet.getInt("points"));


                listeCartesFidelite.add(carteFidelite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCartesFidelite;
    }

    public void mettreAJourPointsCarteFideliteAvecUtilisateur(int idCarte, int nouveauxPoints) {
        String sql = "UPDATE cartes_fidelite SET points = ? WHERE id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, nouveauxPoints);
            preparedStatement.setInt(2, idCarte);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public int consulterSoldePointsAvecUtilisateur(int idCarte) {
        int soldePoints = 0;
        String sql = "SELECT lc.points " +
                "FROM cartes_fidelite lc " +
                "JOIN user u ON lc.id_user = u.id_user " +
                "WHERE lc.id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCarte);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    soldePoints = resultSet.getInt("points");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return soldePoints;
    }




    public User consulterUtilisateurParIdCarte(int idCarte) {
        User utilisateur = null;
        String sql = "SELECT u.id_user, u.first_name, u.username " +
                "FROM cartes_fidelite cf " +
                "JOIN user u ON cf.id_user = u.id_user " +
                "WHERE cf.id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, idCarte);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    utilisateur = new User();
                    utilisateur.setId(resultSet.getInt("id_user"));
                    utilisateur.setFirstName(resultSet.getString("first_name"));
                    utilisateur.setUsername(resultSet.getString("username"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }


    public void echangerPoints(int cardId, int pointsToRedeem) {
        String sql = "UPDATE cartes_fidelite SET points = points - ? WHERE id_carte = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, pointsToRedeem);
            preparedStatement.setInt(2, cardId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void AJOUTERPoints(int idUser, int pointsToRedeem) {
        String sql = "UPDATE cartes_fidelite SET points = points + ? WHERE id_user = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, pointsToRedeem);
            preparedStatement.setInt(2, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int soldecarte(int cardId) {
        int pointsBalance = 0;
        String sql = "SELECT points FROM cartes_fidelite  WHERE card_id = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, cardId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pointsBalance = resultSet.getInt("points");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pointsBalance;
    }

    public int soldecarteiduser(int iduser) {
        int pointsBalance = 0;
        String sql = "SELECT points FROM cartes_fidelite  WHERE id_user = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
            preparedStatement.setInt(1, iduser);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    pointsBalance = resultSet.getInt("points");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pointsBalance;
    }
    public int carteExistsForUser(int userId) {
        int cardId = -1;
        String sql = "SELECT id_carte FROM cartes_fidelite WHERE id_user = ?";

        try (PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // If the result set has at least one row, get the card ID
                if (resultSet.next()) {
                    cardId = resultSet.getInt("id_carte");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return cardId;
    }


}
