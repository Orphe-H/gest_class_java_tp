package com.tp.etudiant;

public class Matiere {
    public String nom;
    public double note1;
    public double note2;
    public double note3;
    public double moy;

    public String getNom(){
        return nom;
    }
    public void  setNom(String nom){
        this.nom = nom;
    }

    public double getNote1(){
        return note1;
    }
    public void  setNote1(double note1){
        this.note1 = note1;
    }

    public double getNote2(){
        return note2;
    }
    public void  setNote2(double note2){
        this.note2 = note2;
    }

    public double getNote3(){
        return note3;
    }
    public void  setNote3(double note3){
        this.note3 = note3;
    }

    public double getMoy(){
        return moy;
    }
    public void  setMoy(double moy){
        this.moy = moy;
    }

}
