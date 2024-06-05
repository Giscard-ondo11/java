package services;

import java.util.ArrayList;

import entities.Cours;
import entities.Modules;
import repository.CoursRepo;

public class CoursService {
    private CoursRepo coursRepo = new CoursRepo();

    public int insert(Cours cours) {

        return coursRepo.insert(cours);
    }



    public ArrayList<Cours> all() {

        return coursRepo.all();
    }
    public Modules oneByNomModules(String NomModule) {

        return coursRepo.oneByNomModules(NomModule);
    }  
} 

