package com.tp.menu;

import com.tp.etudiant.Etudiant;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Menu {

    public static void main(String[] args){
        int choix, taille = 0, t = 0;
        List<Etudiant> listeEtu = new ArrayList<>();
        Etudiant.initListeEtu(listeEtu);
        t = listeEtu.size();
        Scanner sc = new Scanner(System.in);

        do{
            System.out.print("\n|__________MENU__________|" +
                             //"\n|1- Ajouter 70 Etudiants |" +
                             "\n|  1- Ajouter Etudiants  |" +
                             "\n|  2- Afficher Etudiants |" +
                             "\n|  3- Saisir Notes       |" +
                             "\n|  4- Afficher Rang      |" +
                             "\n|  5- Quitter            |" +
                             "\n|________________________|");

            System.out.print("\n|----->Votre Choix :");
            choix = sc.nextInt();

            switch (choix)
            {
                case 1:
                {
                    do{
                        System.out.print("Combien d'etudiant voulez vous saisir ? : ");
                        sc = new Scanner(System.in);
                        taille = sc.nextInt();
                    }while(taille<1 || taille>70);

                    System.out.print("\n\n");
                    Etudiant.saisirEtu(listeEtu, taille);
                    System.out.print("\n\n");
                }
                break;
                case 2:
                {
                    Etudiant.afficherEtu(listeEtu);
                    System.out.print("\n\n");
                }
                break;
                case 3:
                {
                    Etudiant.saisirNote(listeEtu, taille, t);
                    System.out.print("\n\n");
                }
                break;
                case 4:
                {
                    Etudiant.definirRang(listeEtu, (taille + t), t);
                    System.out.print("\n\n");
                }
                break;
                case 5:
                {
                    System.out.println("\n\nAu revoir ;)");
                }
                break;
                default:
                    System.out.println("Vous avez entrer une valeur incorrecte");
                break;

            }
        }while(choix != 5);

    }
}
