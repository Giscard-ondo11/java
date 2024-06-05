package repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import core.Database;
import entities.Cours;
import entities.Modules;
import entities.Prof;

public class CoursRepo extends Database {
    private ProfRepo profRepo = new ProfRepo();
    private ModulesRepo modulesRepo = new ModulesRepo();
    private final String SQL_FIND_ALL = "select * from cours";
    private final String SQL_FIND_BY_ID = "select * from cours where =?";
    private final String SQL_INSERT = "INSERT INTO `cours` (`libelle`) VALUES (?)";
    public int insert(Cours cours) {
        int id=-1;
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_INSERT);
            statement.setInt(1, cours.getId());
            statement.setDate(2, Date.valueOf(cours.getDate()));
            statement.setInt(3, cours.getHeureDB().getHour()+cours.getHeureDB().getMinute());
            statement.setInt(4, cours.getHeureFin().getHour()+cours.getHeureFin().getMinute());
            statement.setInt(5, cours.getProf().getId());
            statement.setInt(6, cours.getModule().getId());

            id=executeUpdate();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion du cour: " + e.getMessage());
        }
        return id;
    }


        public ArrayList<Cours> all() {
        ArrayList<Cours> cours = new ArrayList<>();
        try {
            ouvrirConnexion();
            initPrepareStatement(SQL_FIND_ALL);
            ResultSet rs = executeSelect();
            while (rs.next()) {
                Cours cour = new Cours();    
                Prof prof = new Prof();  
                Modules modules = new Modules();
                prof =  profRepo.oneByid(rs.getInt("prof"));
                int id=rs.getInt("module");
                modules=modulesRepo.all().stream().filter(module->module.getId()==id).findFirst().orElse(null);                
                cour.setId(rs.getInt("id"));
                cour.setDate(null);
                cour.setHeureDB(rs.getTime("HeureDb").toLocalTime());
                cour.setHeureFin(rs.getTime("HeureFin").toLocalTime());
                cour.setProf(prof);
                cour.setModule(modules);    
                cours.add(cour);
            }
            rs.close();
            fermerConnexion();
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des modules: " + e.getMessage());
        }
        return cours;
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
 