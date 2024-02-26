package tn.esprit.Services;

import tn.esprit.Interfaces.InterfaceCRUD;
import tn.esprit.Models.Commande;
import tn.esprit.Models.Livraison;
import tn.esprit.Utils.MaConnexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceLivraison implements InterfaceCRUD<Livraison> {
    Connection conn= MaConnexion.getInstance().getConn();
//ajouter livraison
    @Override
    public void ajouter(Livraison l) {
        String req = "INSERT INTO livraison (id_cmd, id_user, status,adresse,frais_liv,moyen_livraison) VALUES (?, ?, ?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, l.getId_cmd());
            ps.setInt(2, l.getId_user());
            ps.setString(3, l.getStatus());
            ps.setString(4, l.getAdresse());
            ps.setString(5, l.getFrais_liv());
            ps.setString(6, l.getMoyen_livraison());
            ps.executeUpdate();
            System.out.println("Livraison ajoutée avec succés!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


}
    //modifier livraison
    @Override
    public void modifier(Livraison l) {
        String req = "update livraison set id_cmd=? , id_user=? , status=?, adresse=?,frais_liv=?,moyen_livraison=? where id_liv=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setInt(1, l.getId_cmd());
            ps.setInt(2, l.getId_user());
            ps.setString(3, l.getStatus());
            ps.setString(4, l.getAdresse());
            ps.setString(5, l.getFrais_liv());
            ps.setString(6, l.getMoyen_livraison());
            ps.setInt(7, l.getId_liv());

            // Exécute la requête de mise à jour
            ps.executeUpdate();

            System.out.println("Livraison modifiée avec succès");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //supprimer livraison
    @Override
    public void supprimer(int id) {
        String req = " delete from livraison where id_liv=?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
        ps.setInt(1,id);
        ps.executeUpdate();
            System.out.println("livraison supprimée avec succés!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void annulerCommande(int id) throws SQLException {

    }

    //afficher livraison
    @Override
    public List<Livraison> afficher() throws SQLException{
        List<Livraison> livraisons = new ArrayList<>();
        String req ="select * from livraison";
        Statement st = conn.createStatement();
        ResultSet res =st.executeQuery(req);
        while(res.next()){
            Livraison l = new Livraison();
            l.setId_liv(res.getInt(1));
            l.setId_cmd(res.getInt(2));
            l.setId_user(res.getInt(3));
            l.setStatus(res.getString(4));
            l.setAdresse(res.getString(5));
            l.setFrais_liv(res.getString(6));
            l.setMoyen_livraison(res.getString(6));


            livraisons.add(l);}return livraisons;
    }

    public int getIdUtilisateurParNomComplet(String nomComplet) throws SQLException {
        int idUtilisateur = -1; // Valeur par défaut si aucun utilisateur n'est trouvé
        String req = "SELECT id_user FROM user WHERE CONCAT(nom, ' ', prenom) = ?";
        try (PreparedStatement ps = conn.prepareStatement(req)) {
            ps.setString(1, nomComplet);
            try (ResultSet res = ps.executeQuery()) {
                if (res.next()) {
                    idUtilisateur = res.getInt("id_user");
                }
            }
        }
        return idUtilisateur;
    }
    // ServiceCommande
    public Map<String, Integer> getAllProjectTitlesAndIds() throws SQLException {
        Map<String, Integer> projectTitlesAndIds = new HashMap<>();
        String req = "SELECT id_projet, titre FROM projet";
        try (PreparedStatement ps = conn.prepareStatement(req);
             ResultSet res = ps.executeQuery()) {
            while (res.next()) {
                int id_projet = res.getInt("id_projet");
                String titre = res.getString("titre");
                projectTitlesAndIds.put(titre, id_projet);
            }
        }
        return projectTitlesAndIds;
    }
}
