package com.ipiecoles.java.java220;

import com.sun.corba.se.impl.naming.namingutil.INSURL;
import org.joda.time.LocalDate;

import java.io.ObjectStreamException;
import java.util.Objects;


//enum note { INSUFFISANT, PASSABLE, BIEN, TRES_BIEN };

public class Commercial extends Employe{
    private Double caAnnuel;
    private Integer performance;
    private Note note;

    public Commercial() {
        super();
        this.caAnnuel = null;
        this.performance = null;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel = caAnnuel;
    }

    public Commercial(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Double caAnnuel, Integer performance) {
        super(nom, prenom, matricule, dateEmbauche, salaire);
        this.caAnnuel = caAnnuel;
        this.performance = performance;
    }

    public Integer getPerformance() {
        return this.performance;
    }

    public Double getCaAnnuel() {
        return this.caAnnuel;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public void setCaAnnuel(Double ca){
       this.caAnnuel = ca;
    }

    public boolean performanceEgale(Integer performance) {
        return Objects.equals(this.performance, performance);
    }

    @Override
    public Double getPrimeAnnuelle() {
        Double ca;
        if(this.caAnnuel == null) { ca = 1.00; } else { ca = caAnnuel; }
        Double prime = (ca / 100.0) * 5.0;
        if(prime < 500.0) { prime = 500.0; }
        return Math.ceil(prime);
    }

    public boolean equals(Object o) {
        if(!(o instanceof Commercial)) { return false; }
        if(this == o) { return true; }
        Commercial c = (Commercial) o;
        if(!(super.equals(o))) { return false; }
        if(!(Objects.equals(this.caAnnuel, c.caAnnuel))) { return false; }
        return true;
    }

    public Note equivalenceNote() {
        switch(this.performance) {
            case 0:
                //this.note = note.INSUFFISANT;
                return note.INSUFFISANT;
            case 50:
                //this.note = note.INSUFFISANT;
                return note.INSUFFISANT;
            case 100:
                //this.note = note.PASSABLE;
                return note.PASSABLE;
            case 150:
                //this.note = note.BIEN;
                return note.BIEN;
            case 200:
                //this.note = note.TRES_BIEN;
                return note.TRES_BIEN;
            default:
                //this.note = note.TRES_BIEN;
                return note.TRES_BIEN;
        }
    }

    public Note getNote() {
        return this.note;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.performance, this.caAnnuel, this.note);
    }

    @Override
    public String toString() {
        return "Commercial{caAnnuel="+this.getCaAnnuel()+",performance="+this.getPerformance()+",note="+this.getNote()+"} "+super.toString();
    }

}
