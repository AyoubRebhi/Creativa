package tn.esprit.Services;
import tn.esprit.Interfaces.InterfaceCodePromo;
import tn.esprit.Models.Codepromo;
import tn.esprit.Models.Commande;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceCodepromo implements InterfaceCodePromo<Codepromo> {
    Connection conn = MaConnexion.getInstance().getConn();


    @Override
    public void ajouter(Codepromo c) {
        String req = "INSERT INTO code_promo (code_promo,pourcentage) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getCode_promo());
            ps.setString(2, c.getPourcentage());
            ps.executeUpdate();
            System.out.println("Code promo ajouté avec succés!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Codepromo rechercher(Codepromo c) {
        String req = "SELECT * FROM code_promo WHERE code_promo = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, c.getCode_promo()); // Utilisez getCode_promo() de l'objet c pour obtenir le code promo

            // Exécuter la requête de recherche
            ResultSet rs = ps.executeQuery();

            // Vérifier si le résultat de la requête est vide
            if (!rs.next()) {
                System.out.println("Aucun code promo trouvé avec ce code.");
                return null; // Aucun code promo trouvé, renvoie null
            } else {
                // Si un code promo est trouvé, créer un nouvel objet Codepromo avec les détails et le retourner
                String pourcentage = rs.getString("pourcentage");

                Codepromo codepromoTrouve = new Codepromo(c.getCode_promo(), pourcentage);
                return codepromoTrouve;
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
                c.setCode_promo(res.getInt(1));
                c.setPourcentage(res.getString(2));
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

}

