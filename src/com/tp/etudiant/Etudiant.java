package com.tp.etudiant;

import java.io.IOException;
import java.util.*;

public class Etudiant {

    private String nom;
    private String prenom;
    private List<Matiere> listeNote;
    private double moyenne;

    public String getNom(){
        return nom;
    }
    public void  setNom(String nom){
        this.nom = nom;
    }

    public String getPrenom(){
        return prenom;
    }
    public void  setPrenom(String prenom){
        this.prenom = prenom;
    }


    public double getMoyenne(){
        return moyenne;
    }
    public void setMoyenne(double moyenne){
        this.moyenne = moyenne;
    }

    public List<Matiere> getListeNote(){
        return listeNote;
    }
    public void  setListeNote(List<Matiere> listeNote){
        this.listeNote = listeNote;
    }


    public static void saisirEtu(List<Etudiant> listeEtu, int taille){

        for(int i=0; i<taille; i++){
            Etudiant etu = new Etudiant();

            System.out.println("\nEtudiant n°"+ (i+1));
            System.out.print("Saisissez le nom : ");
            Scanner sc = new Scanner(System.in);
            String chaine = sc.nextLine();
            etu.setNom(chaine);

            System.out.print("Saisissez le prenom : ");
            sc = new Scanner(System.in);
            chaine = sc.nextLine();
            etu.setPrenom(chaine);

            listeEtu.add(etu);
        }
    }

    public static void afficherEtu(List<Etudiant> listeEtu){
        for(Etudiant elem: listeEtu)
        {
            System.out.print("Etudiant n°"+ (listeEtu.indexOf(elem) + 1) + ": ");
            System.out.print (elem.getNom() + " "+ elem.getPrenom() + "\n");
        }
    }

    public static void saisirNote(List<Etudiant> listeEtu, int taille, int nbPreRempli){
        Scanner sc;
        double note = 0.0;

        for(int i=0; i<taille; i++){
            Etudiant etu = new Etudiant();
            List<Matiere> listMat = new ArrayList<>();
            System.out.println("\nEtudiant n°"+ (i+1+nbPreRempli) + ": " + listeEtu.get(i+nbPreRempli).getNom() +
                    " " + listeEtu.get(i+nbPreRempli).getPrenom());

            for(int j=0; j<12; j++) {
                Matiere mat = new Matiere();

                if (j == 0)
                    mat.setNom("Java");
                else if (j == 1)
                    mat.setNom("C");
                else if (j == 2)
                    mat.setNom("POO");
                else if (j == 3)
                    mat.setNom("Anglais");
                else if (j == 4)
                    mat.setNom("TEEO");
                else if (j == 5)
                    mat.setNom("BDD");
                else if (j == 6)
                    mat.setNom("Php");
                else if (j == 7)
                    mat.setNom("Topologie");
                else if (j == 8)
                    mat.setNom("Outils Bureautique");
                else if (j == 9)
                    mat.setNom("Algo");
                else if (j == 10)
                    mat.setNom("CCNA");
                else//cas j=11
                    mat.setNom("Xml");

                System.out.println("\nMatiere " + (j+1) + " : "+ mat.getNom());
                System.out.print("Note 1 : ");
                sc = new Scanner(System.in);
                note = sc.nextDouble();
                mat.setNote1(note);

                System.out.print("Note 2 : ");
                sc = new Scanner(System.in);
                note = sc.nextDouble();
                mat.setNote2(note);

                System.out.print("Note 3 : ");
                sc = new Scanner(System.in);
                note = sc.nextDouble();
                mat.setNote3(note);

                mat.setMoy((mat.getNote1() + mat.getNote2() + mat.getNote3()) / 3);
                listMat.add(mat);
            }

            etu.setListeNote(listMat);
            etu.setMoyenne(calculMoy(listMat));

            for(Etudiant elem: listeEtu)
            {
                if(listeEtu.indexOf(elem) == (i+ nbPreRempli)){
                    etu.setNom(elem.getNom());
                    etu.setPrenom(elem.getPrenom());
                    break;
                }
            }
            listeEtu.set((i + nbPreRempli), etu);
        }
    }

    public static double calculMoy(List<Matiere> listMat){
        double total = 0.0;
        for(Matiere elem: listMat){
            total = total + elem.getMoy();
        }
        return (total/ 12);
    }

    public static void  definirRang(List<Etudiant> listeEtu, int taille, int t){
        HashMap mapM = new HashMap();
        int i = 0, rang = 0;
        double lastM = 0.0;
        List<Integer> listId = new ArrayList<>();
        List<Double> listMoyenne = new ArrayList<>();

        for(Etudiant elem: listeEtu){
            mapM.put(listeEtu.indexOf(elem), elem.getMoyenne());
        }

        HashMap mapTri = triAvecValeur(mapM);

        Iterator it = mapTri.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();

            listId.add((Integer) m.getKey());
            listMoyenne.add((Double) m.getValue());

        }

        if(taille != t){

            System.out.println("\n RANG        NOM        PRENOM     MOYENNE  ");
            for(i=0; i<taille; i++){
                int currentId = listId.get(i);
                if(lastM == listMoyenne.get(i)){
                    System.out.println("  " + (rang+1) + "ex  \t"+ listeEtu.get(currentId).getNom() + "\t       " + listeEtu.get(currentId).getPrenom()+ "\t   "+
                            listMoyenne.get(i) );
                }
                else{
                    rang = i;
                    lastM = listMoyenne.get(i);
                    System.out.println("   " + (rang+1) + "     \t"+ listeEtu.get(currentId).getNom() + "\t       " + listeEtu.get(currentId).getPrenom() + "\t   "+
                            listMoyenne.get(i) );
                }
            }
        }
        else {
            System.out.println("\n|_RANG__|_____NOM____|___PRENOM___|_MOYENNE_|");
            for(i=0; i<taille; i++){
                int currentId = listId.get(i);
                if(lastM == listMoyenne.get(i)){
                    System.out.println("| " + (rang+1) + "ex \t|"+ listeEtu.get(currentId).getNom() + "\t     | " + listeEtu.get(currentId).getPrenom()+ "\t  |"+
                            listMoyenne.get(i) );
                }
                else{
                    rang = i;
                    lastM = listMoyenne.get(i);
                    System.out.println("|  " + (rang+1) + "\t|"+ listeEtu.get(currentId).getNom() + "\t     | " + listeEtu.get(currentId).getPrenom() + "\t  |"+
                            listMoyenne.get(i) );
                }
            }
            System.out.print("|_______|____________|____________|_________|");

        }


    }

    public static HashMap<Integer, Double> triAvecValeur( HashMap<Integer, Double> map ){
        List<Map.Entry<Integer, Double>> list = new LinkedList<Map.Entry<Integer, Double>>( map.entrySet() );

        Collections.sort( list, new Comparator<Map.Entry<Integer, Double>>(){
            public int compare( Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2 ){
                return (o2.getValue()).compareTo( o1.getValue());

            }
        });

        HashMap<Integer, Double> map_apres = new LinkedHashMap<Integer, Double>();
        for(Map.Entry<Integer, Double> entry : list)
            map_apres.put( entry.getKey(), entry.getValue() );
        return map_apres;
    }


    public static void initListeEtu(List<Etudiant> listeEtu){

        Etudiant etu = new Etudiant();
        etu.setNom("Nom1"); etu.setPrenom("Pre1 "); etu.setMoyenne(10.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom2"); etu.setPrenom("Pre2 "); etu.setMoyenne(9.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom3"); etu.setPrenom("Pre3 "); etu.setMoyenne(9.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom4"); etu.setPrenom("Pre4 "); etu.setMoyenne(8.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom5"); etu.setPrenom("Pre5 "); etu.setMoyenne(8.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom6"); etu.setPrenom("Pre6 "); etu.setMoyenne(7.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom7"); etu.setPrenom("Pre7 "); etu.setMoyenne(7.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom8"); etu.setPrenom("Pre8 "); etu.setMoyenne(6.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom9"); etu.setPrenom("Pre9 "); etu.setMoyenne(6.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom10"); etu.setPrenom("Pre10"); etu.setMoyenne(5.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom11"); etu.setPrenom("Pre11"); etu.setMoyenne(5.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom11"); etu.setPrenom("Pre11"); etu.setMoyenne(4.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom12"); etu.setPrenom("Pre12"); etu.setMoyenne(4.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom13"); etu.setPrenom("Pre13"); etu.setMoyenne(10.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom14"); etu.setPrenom("Pre14"); etu.setMoyenne(11.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom15"); etu.setPrenom("Pre15"); etu.setMoyenne(11.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom16"); etu.setPrenom("Pre16"); etu.setMoyenne(12.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom17"); etu.setPrenom("Pre17"); etu.setMoyenne(12.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu = new Etudiant();
        etu.setNom("Nom18"); etu.setPrenom("Pre18"); etu.setMoyenne(13.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom19"); etu.setPrenom("Pre19"); etu.setMoyenne(13.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom20"); etu.setPrenom("Pre20"); etu.setMoyenne(14.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom21"); etu.setPrenom("Pre21"); etu.setMoyenne(14.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom22"); etu.setPrenom("Pre22"); etu.setMoyenne(15.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom23"); etu.setPrenom("Pre23"); etu.setMoyenne(15.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom24"); etu.setPrenom("Pre24"); etu.setMoyenne(16);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom25"); etu.setPrenom("Pre25"); etu.setMoyenne(16.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom26"); etu.setPrenom("Pre26"); etu.setMoyenne(15);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom27"); etu.setPrenom("Pre27"); etu.setMoyenne(15.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom28"); etu.setPrenom("Pre28"); etu.setMoyenne(16);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom29"); etu.setPrenom("Pre29"); etu.setMoyenne(16.5);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom30"); etu.setPrenom("Pre30"); etu.setMoyenne(17);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom31"); etu.setPrenom("Pre31"); etu.setMoyenne(8.75);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom32"); etu.setPrenom("Pre32"); etu.setMoyenne(8.85);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom33"); etu.setPrenom("Pre33"); etu.setMoyenne(9.66);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom34"); etu.setPrenom("Pre34"); etu.setMoyenne(10.83);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom35"); etu.setPrenom("Pre35"); etu.setMoyenne(14.66);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom36"); etu.setPrenom("Pre36"); etu.setMoyenne(14.78);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom37"); etu.setPrenom("Pre37"); etu.setMoyenne(14.81);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom38"); etu.setPrenom("Pre38"); etu.setMoyenne(14.59);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom39"); etu.setPrenom("Pre39"); etu.setMoyenne(14.77);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom40"); etu.setPrenom("Pre40"); etu.setMoyenne(13.77);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom41"); etu.setPrenom("Pre41"); etu.setMoyenne(19.81);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom42"); etu.setPrenom("Pre42"); etu.setMoyenne(13.22);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom43"); etu.setPrenom("Pre43"); etu.setMoyenne(14.8521);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom44"); etu.setPrenom("Pre44"); etu.setMoyenne(17.54);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom45"); etu.setPrenom("Pre46"); etu.setMoyenne(12.33);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom46"); etu.setPrenom("Pre46"); etu.setMoyenne(13.25);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom47"); etu.setPrenom("Pre21"); etu.setMoyenne(16.0);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom48"); etu.setPrenom("Pre48"); etu.setMoyenne(16.55);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom50"); etu.setPrenom("Pre50"); etu.setMoyenne(17.33);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom51"); etu.setPrenom("Pre51"); etu.setMoyenne(18.99);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom52"); etu.setPrenom("Pre52"); etu.setMoyenne(17.33);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom53"); etu.setPrenom("Pre53"); etu.setMoyenne(17.13);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom54"); etu.setPrenom("Pre54"); etu.setMoyenne(17.03);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom55"); etu.setPrenom("Pre55"); etu.setMoyenne(16.04);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom56"); etu.setPrenom("Pre56"); etu.setMoyenne(10.01);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom57"); etu.setPrenom("Pre57"); etu.setMoyenne(14.34);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom58"); etu.setPrenom("Pre58"); etu.setMoyenne(9.67);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom59"); etu.setPrenom("Pre59"); etu.setMoyenne(18.05);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom60"); etu.setPrenom("Pre60"); etu.setMoyenne(12.11);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom61"); etu.setPrenom("Pre61"); etu.setMoyenne(12.05);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom62"); etu.setPrenom("Pre561"); etu.setMoyenne(12.17);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom63"); etu.setPrenom("Pre63"); etu.setMoyenne(11.11);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom63"); etu.setPrenom("Pre63"); etu.setMoyenne(12.26);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom64"); etu.setPrenom("Pre64"); etu.setMoyenne(9.12);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom65"); etu.setPrenom("Pre65"); etu.setMoyenne(17.33);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom66"); etu.setPrenom("Pre67"); etu.setMoyenne(9.09);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom67"); etu.setPrenom("Pre68"); etu.setMoyenne(7.67);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom68"); etu.setPrenom("Pre69"); etu.setMoyenne(9.09);
        listeEtu.add(etu);
        etu = new Etudiant();
        etu.setNom("Nom70"); etu.setPrenom("Pre70"); etu.setMoyenne(9.51);
        listeEtu.add(etu);

    }

}
