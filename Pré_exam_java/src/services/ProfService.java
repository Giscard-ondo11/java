package services;


import entities.Prof;
import repository.ProfRepo;

public class ProfService {
    private ProfRepo profRepo = new ProfRepo();


     public Prof oneByid(int id) {
        
        return profRepo.oneByid(id);
    }
    public int insert(Prof prof) {
       
        return profRepo.insert(prof);
    }

}
