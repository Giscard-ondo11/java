package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cours {
    private int id;
    private LocalDate date;
    private LocalTime HeureDB;
    private LocalTime HeureFin;
    private Prof prof;
    private Modules module;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public LocalTime getHeureDB() {
        return HeureDB;
    }
    public void setHeureDB(LocalTime heureDB) {
        HeureDB = heureDB;
    }
    public LocalTime getHeureFin() {
        return HeureFin;
    }
    public void setHeureFin(LocalTime heureFin) {
        HeureFin = heureFin;
    }
    public Prof getProf() {
        return prof;
    }
    public void setProf(Prof prof) {
        this.prof = prof;
    }
    public Modules getModule() {
        return module;
    }
    public void setModule(Modules module) {
        this.module = module;
    }

}
