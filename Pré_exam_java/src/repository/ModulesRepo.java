package repository;

import java.sql.ResultSet;
import java.util.ArrayList;

import core.Database;
import entities.Modules;


public class ModulesRepo extends Database {  
    private final String SQL_FIND_ALL = "select * from module";
    private final String SQL_FIND_BY_ID = "select * from module where id=?";
    private final String SQL_INSERT = "INSERT INTO `module` (`libelle`) VALUES (?)";
    
    public int insert(Modules module) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setString(1, module.getNomModule());
            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion du module : " + e.getMessage());
        }
        return id;
    }

        public ArrayList<Modules> all() {
        ArrayList<Modules> modules = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Modules module = new Modules();                
                module.setId(rs.getInt("id"));
                module.setNomModule(rs.getString("Nom Module"));                
                modules.add(module);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des modules: " + e.getMessage());
        }
        return modules;
    }
    public Modules oneByNomModules(String NomModule) {
        Modules module = null;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_BY_ID);
            statement.setString(1, NomModule);
            ResultSet rs = executeSelect();
            if (rs.next()) {
                module = new Modules();
                
                module.setId(rs.getInt("id"));
                module.setNomModule(rs.getString("Nom module"));
                
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des Modules: " + e.getMessage());
        }
        return module;
    }   
}


