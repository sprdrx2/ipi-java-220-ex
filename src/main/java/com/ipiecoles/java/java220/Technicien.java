package com.ipiecoles.java.java220;

import org.joda.time.LocalDate;

import java.util.Objects;

public class Technicien extends Employe implements Comparable<Technicien> {

    private Integer grade;

    public Technicien() {
        super();
        this.grade = null;
    }

    public Technicien(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer grade) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.grade = grade;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) throws TechnicienException {
        if((grade > 5) || (grade == 0)) {
            //this.grade = grade;
            throw new TechnicienException("Le grade doit être compris entre 1 et 5 : "+grade+", technicien : "+this.toString());
        } else {
            this.grade = grade;
        }
    }

    public String toString() {
        return "Technicien{grade="+this.grade+"} "+super.toString();
    }

    public Double getSalaire() {
        return super.getSalaire() + (this.getGrade() * 100);
    }

    public Integer getNbConges() {
        return super.getNbConges() + getNombreAnneeAnciennete();
    }

    public Double getPrimeAnnuelle() {
        Double primeB  = Entreprise.primeAnnuelleBase();
        Double bonusG = (primeB / 10.00) * this.getGrade();
        Double primeA = Entreprise.PRIME_ANCIENNETE * super.getNombreAnneeAnciennete();

        return primeB + bonusG + primeA;
    }

    @Override
    public int compareTo(Technicien t) {
        if(this.grade < t.grade) { return -1; }
        else if (this.grade > t.grade) { return 1; }
        else { return 0; }
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getGrade());
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Technicien)) { return false; }
        if(this == o) { return true; }
        Technicien t = (Technicien) o;
        if(!(super.equals(o))) { return false; }
        if(!(Objects.equals(this.getGrade(), t.getGrade()))) { return false; }
        return true;
    }

}

