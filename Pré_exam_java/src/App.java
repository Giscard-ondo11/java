import java.util.ArrayList;
import java.util.Scanner;

import entities.Cours;
import entities.Modules;
import entities.Prof;
import services.CoursService;
import services.ModulesService;
import services.ProfService;

public class App {
 private static Scanner scanner=new Scanner(System.in);
    private static CoursService coursService= new CoursService();
    private static ModulesService modulesService= new ModulesService();
    private static ProfService profService= new ProfService();


    public static void main(String[] args) throws Exception {
        int choix, choix_classe;
        Cours cour;
        Modules modules;
        ArrayList<Cours> cours=new ArrayList<>();
        Prof prof;
        do {
                System.out.println("---------------- MENU ------------------");   
                System.out.println("\n1- Ajouter un Module");
                System.out.println("2- Lister les modules");
                System.out.println("3- Creer un cours");
                System.out.println("4- Lister les  tous les cours");
                System.out.println("5- Lister les cours  d’un module et d’un professeur");
                System.out.println("6- Quitter");
                System.out.print("Faites votre choix: ");
                choix = scanner.nextInt();
                scanner.nextLine();
                switch (choix) {
                    case 1:
                        modules = new Modules();
                        System.out.print("Entrer le libelle ");
                        modules.setNomModule(scanner.nextLine());
                        // 
                        if (modulesService.insert(modules)!=-1){
                            System.out.println("\n--------------------  Reussi -----------------------\n");
                        }else{
                            System.out.println("\n--------------------  ECHEC ----------------------\n");
                        }
                    
                        break;
                    case 2:
                        System.out.println("\n--------------- LES  MODULES -------------------\n");
                        for (Modules m : modulesService.all()) {
                            System.out.println(m);
                        }
                    break;
                    case 3:
                        classes=new ArrayList<>();
                        // 
                        prof = new Prof();
                        // 
                        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.print("Entrer le nom du professeur: ");
                        prof.setNomComplet(scanner.nextLine());
                        System.out.print("Entrer le grade du professeur: ");
                        prof.setGrade(scanner.nextLine()); 
                        do {
                            System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.print("Entrer la classe a affecter a ce professeur[-1 pour sortir]: ");
                            choix_classe=scanner.nextInt();
                            if(choix_classe!=-1){
                                scanner.nextLine();
                                classe = classeService.selectById(choix_classe);
                                if(classe!=null){
                                    if (recherClasse(classes, classe)==null){
                                        classes.add(classe);
                                        System.out.println("\n= Classe affecter avec success =\n");
                                    }else{
                                        System.out.println("\n= Vouz avez deja affecter cette classe a ce professeur =\n");
                                    }
                                }else{
                                    System.out.println("\n= Classe introuvable =\n");
                                }
                            }
                        } while (choix_classe!=-1);

                        if(classes.size()>0){
                            int profId = profService.insert(prof);
                            if (profId!=-1){
                                prof.setId(profService.maxId());
                                for (Classe cl : classes) {
                                    classeProfService.insert(new ClasseProf(0, cl, prof));
                                }
                                System.out.println("\n= Professeur ajouter avec succes =\n");
                            }else{
                                System.out.println("\n= Erreur lors de l'ajout du professeur =\n");
                            }
                        }
                    break;
                    case 4:
                        System.out.println("\n-------------- LES COURS -------------------\n");
                        for (Cours cr : coursService.all()) {
                            System.out.println(cr);
                        }
                    break;
                    case 5:
                        System.out.print("Entrer l'id du professeur: ");
                        Prof prof0=profService.selectById(scanner.nextInt());
                        if(prof0!=null){
                            System.out.println("Nom: "+prof0.getNomComplet());
                            System.out.println("Grade: "+prof0.getGrade());
                            for (ClasseProf clPr : classeProfService.selectByProf(prof0)) {
                                System.out.println("Libelle: "+clPr.getClasse().getLibelle());
                            }
                        }else{
                            System.out.println("\n= Professeur introuvable =\n");
                        }
                    break;
                  
                
                    default:
                        break;
                }
        } while (choix!=6);

    
        scanner.close();
    }

    
}
