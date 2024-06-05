package repository;

import java.sql.ResultSet;

import core.Database;
import entities.Prof;

public class ProfRepo extends Database {
    protected final String SQL_INSERT = "INSERT INTO `prof` (`Nom`,'Prenom', `Tel`) VALUES (?, ?,?)";
    protected final String SQL_FIND_BY_ID = "select * from prof where id=?";


        public Prof oneByid(int id) {
        Prof prof = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                prof = new Prof();
                
                prof.setId(rs.getInt("id"));
                prof.setNom(rs.getString("nomComplet"));
                prof.setPrenom(rs.getString("Prenom"));
                prof.setTel(rs.getInt("Tel"));
                
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des Tels: " + e.getMessage());
        }
        return prof;
    }
    public int insert(Prof prof) {
        int id=-1;
        try{
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, prof.getNom());
            statement.setString(2, prof.getPrenom());
            statement.setInt(3, prof.getTel());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion du Prof: " + e.getMessage());
        }
        return id;
    }

}
