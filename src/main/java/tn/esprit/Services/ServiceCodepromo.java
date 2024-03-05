package tn.esprit.Services;
import tn.esprit.Interfaces.InterfaceCodePromo;
import tn.esprit.Models.Codepromo;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ServiceCodepromo implements InterfaceCodePromo<Codepromo> {
    static Connection conn = MaConnexion.getInstance().getConn();


    @Override
    public void ajouter(Codepromo c) {
        String req = "INSERT INTO code_promo (id, code_promo, pourcentage, date,date_expiration) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getId());
            ps.setInt(2, c.getCode_promo());
            ps.setString(3, c.getPourcentage());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now().plusDays(4))); // Date d'expiration = date actuelle + 2 minutes

            ps.executeUpdate();
            System.out.println("Code promo ajouté avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Codepromo rechercher(Codepromo c) {
        String req = "SELECT * FROM code_promo WHERE code_promo = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getCode_promo());

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Aucun code promo trouvé avec ce code.");
                return null;
            } else {
                String pourcentage = rs.getString("pourcentage");
                Date dateExpiration = rs.getDate("date_expiration");
                Date date = rs.getDate("date");

                // Vérifie si la date actuelle est avant la date d'expiration
                if (LocalDate.now().isBefore(dateExpiration.toLocalDate())) {
                    Codepromo codepromoTrouve = new Codepromo(c.getId(), c.getCode_promo(), pourcentage,date, dateExpiration);
                    return codepromoTrouve;
                } else {
                    System.out.println("Le code promo n'est plus valide.");
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la recherche du code promo", e);
        }
    }

    @Override
    public List<Codepromo> afficher() throws SQLException {
        List<Codepromo> codepromo = new ArrayList<>();
        String req = "select * from code_promo";
        try (Statement st = conn.createStatement();
             ResultSet res = st.executeQuery(req)) {
            while (res.next()) {
                Codepromo c = new Codepromo();
                c.setId(res.getInt(1));
                c.setCode_promo(res.getInt(2));
                c.setPourcentage(res.getString(3));
                c.setDate(res.getDate(4));
                codepromo.add(c);
            }
            return codepromo;
        }
    }

    public boolean codePromoExiste(String codePromo) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean codeExiste = false;

        try {
            // Requête SQL pour vérifier l'existence du code promo
            String sql = "SELECT COUNT(*) AS count FROM code_promo WHERE code_promo = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, codePromo);

            // Exécution de la requête
            resultSet = statement.executeQuery();

            // Récupération du résultat
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                codeExiste = (count > 0); // Le code existe si le résultat est supérieur à zéro
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codeExiste;
    }

    public Codepromo lastCodePromo() {
        String req = "SELECT * FROM code_promo ORDER BY id DESC LIMIT 1";
        try (Statement st = conn.createStatement();
             ResultSet res = st.executeQuery(req)) {
            if (res.next()) {
                Codepromo c = new Codepromo();
                c.setId(res.getInt(1));
                c.setCode_promo(res.getInt(2));
                c.setPourcentage(res.getString(3));
                c.setDate(res.getDate(4));
                c.setDate_expiration(res.getDate(5));
                return c;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Ajoutez un return null à la fin de la méthode pour gérer les cas où aucun code promo n'est trouvé ou s'il y a une exception SQL.
        System.out.println("Aucun code promo trouvé.");
        return null;

    }
//pour calculer le pourcentage
    public String getPourcentageByCodePromo(int codePromo) {
        String pourcentage = null;
        String req = "SELECT pourcentage FROM code_promo WHERE code_promo = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, codePromo);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    pourcentage = res.getString("pourcentage");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pourcentage;
    }

    public static boolean codePromoValide(int codePromo) {
        String req = "SELECT * FROM code_promo WHERE code_promo = ? AND date_expiration > ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, codePromo);
            ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            try (ResultSet res = ps.executeQuery()) {
                return res.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}