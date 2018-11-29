package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.*;
import java.util.stream.*;

public class Manager extends Employe {
    private HashSet<Technicien> equipe = new HashSet<Technicien>();

    public HashSet<Technicien> getEquipe() {
        return this.equipe;
    }

    public void setEquipe(HashSet<Technicien> equipe) {
        this.equipe = equipe;
    }

    public void ajoutTechnicienEquipe(Technicien technicien) {
            this.equipe.add(technicien);
    }

    public void ajoutTechnicienEquipe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        Technicien technicien = new Technicien(nom, prenom, matricule, dateEmbauche, salaire, grade);
        ajoutTechnicienEquipe(technicien);
    }

    @Override
    public void setSalaire(Double salaire) {
        double salaireBase  = salaire;
        double primeManager = (salaire * Entreprise.INDICE_MANAGER) - salaire;
        double primeEquipe  = (salaire / 100) * (this.equipe.size() * 10);
        double salaireTotal = salaireBase + primeEquipe + primeManager;
        super.setSalaire(salaireTotal);
    }

    @Override
    public Double getPrimeAnnuelle() {
        Double primeTechniciens = 0.00;
        if(!this.equipe.isEmpty()) {
            primeTechniciens = Entreprise.PRIME_MANAGER_PAR_TECHNICIEN * this.equipe.size();
        }
        //Double pb = Entreprise.primeAnnuelleBase();
        return Entreprise.primeAnnuelleBase() + primeTechniciens;
    }

    private void augmenterSalaireEquipe(Double augmentation) {
        this.equipe.forEach(t -> {
           Employe e = (Employe) t;
           e.augmenterSalaire(augmentation);
           Technicien t2 = (Technicien) e;
           System.out.println(t2);
        });
    }

    @Override
    public void augmenterSalaire(Double augmentation) {
        augmenterSalaireEquipe(augmentation);
        super.augmenterSalaire(augmentation);
    }

    /*public HashSet<Technicien> equipeParGrade() {
        HashSet<Technicien> techniciens = new HashSet<Technicien>();
        Stream<Technicien> streamT = this.equipe.stream();
        streamT.sorted(Comparator.comparing(Technicien::getGrade()));
        return techniciens;
    }*/

    /*public HashSet<Technicien> equipeParGrade(Integer grade){
        HashSet<Technicien> techniciens = new HashSet<Technicien>();
        Stream<Technicien> streamT = this.equipe.stream();
        return techniciens;
    }*/

    public Double salaireEquipeGrade(Integer grade) {
        Stream<Technicien> streamT = this.equipe.stream();
        Optional<Double> totalSalaire = streamT.filter(t -> t.getGrade().equals(grade)).map(t -> t.getSalaire()).reduce((t1,t2) -> t1 + t2);
        return totalSalaire.orElse(0.00);
    }

    public Double salaireEquipeGrade1() {
        return salaireEquipeGrade(1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getEquipe(), this.getPrimeAnnuelle());
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Manager)) { return false; }
        if(this == o) { return true; }
        Manager m = (Manager) o;
        if(!(super.equals(o))) { return false; }
        if(!(Objects.equals(this.getEquipe(), m.getEquipe()))) { return false; }
        if(!(Objects.equals(this.getPrimeAnnuelle(), m.getPrimeAnnuelle()))) { return false; }
        return true;
    }

    public String toString() {
        return "Manager{sizeEquipe="+this.equipe.size()+",primeAnnuelle="+this.getPrimeAnnuelle()+"} "+super.toString();
    }
}
