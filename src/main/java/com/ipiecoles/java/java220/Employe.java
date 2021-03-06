package com.ipiecoles.java.java220;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.joda.time.LocalDate;

import java.util.Locale;
import java.util.Objects;

/**
 * Created by pjvilloud on 21/09/17.
 */
abstract public class Employe {
    private String nom, prenom, matricule;
    private LocalDate dateEmbauche;
    private Double salaire;

    public Double getSalaire() { return this.salaire; }
    public LocalDate getDateEmbauche() { return this.dateEmbauche; }
    public String getNom() { return this.nom; }
    public String getPrenom(){ return this.prenom; }
    public String getMatricule(){ return this.matricule; }


    public void setSalaire(Double salaire) { this.salaire = salaire; }
    public void setNom(String nom){ this.nom = nom; }
    public void setPrenom(String prenom){ this.prenom = prenom; }
    public void setMatricule(String matricule){ this.matricule = matricule; }

    public void setDateEmbauche(LocalDate date) throws Exception {
        if (date.isAfter(LocalDate.now())) {
            throw new Exception("La date d'embauche ne peut être postérieure à la date courante");
        } else {
            this.dateEmbauche = date;
        }
    }
    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire) {
        setNom(nom);
        setPrenom(prenom);
        setMatricule(matricule);
        //setDateEmbauche(dateEmbauche);
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
    }

    public Employe() {

    }

    public final Integer getNombreAnneeAnciennete() {
        return LocalDate.now().getYear() - this.getDateEmbauche().getYear();
    }

    @Override
    public String toString() {
        return "Employe{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", salaire=" + salaire +
                '}';
    }

    /*public String toString() {
        return String.format("Employe{nom='%s', prenom='%s', matricule='%s', dateEmbauche=%s, salaire=%.1f}", this.nom, this.prenom, this.matricule, this.dateEmbauche.toString(), this.salaire);
    }*/

    public boolean equals(Object o) {
        if (!(o instanceof Employe)) { return false; }
        if(this == o) { return true; }
        Employe e = (Employe) o;
        if(!(Objects.equals(this.nom, e.nom))) { return false; }
        if(!(Objects.equals(this.prenom, e.prenom))) { return false; }
        if(!(Objects.equals(this.matricule, e.matricule))) { return false; }
        if(!(Objects.equals(this.salaire, e.salaire))) { return false; }
        if(!(Objects.equals(this.dateEmbauche, e.dateEmbauche))) { return false; }
        return true;
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE;
    }

    public int hashCode() {
        return Objects.hash(this.nom, this.prenom, this.matricule, this.dateEmbauche, this.salaire);
    }

    public void augmenterSalaire(Double augmentation) {
        if(this.salaire > 0.0 && augmentation > 0.0) {
            this.salaire += this.salaire * augmentation;
        }
    }

    abstract public Double getPrimeAnnuelle();
}
