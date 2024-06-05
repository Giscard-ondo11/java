package services;

import java.util.ArrayList;

import entities.Modules;
import repository.ModulesRepo;

public class ModulesService {
    
    private ModulesRepo modulesRepo = new ModulesRepo();


    public int insert(Modules modules) {

        return modulesRepo.insert(modules);
    }

    public ArrayList<Modules> all() {

        return modulesRepo.all();
    }
    public Modules oneByNomModules(String NomModule) {

        return modulesRepo.oneByNomModules(NomModule);
    }  
    
}
