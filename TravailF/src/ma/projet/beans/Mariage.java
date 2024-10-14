/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

/**
 *
 * @author OUMAIMA
 */
@Entity
public class Mariage {
    @EmbeddedId
   private MariagePk mariagePk ;
    @ManyToOne
    private Femme femme;
    @ManyToOne
    private Homme hommee;
    private int nbrEnfant;
    
    private Date datefin;

   

    public Mariage(MariagePk mariagePk, Femme femme, Homme homme, Date datefin) {
        this.mariagePk = mariagePk;
        this.femme = femme;
        this.hommee = homme;
       
        this.datefin = datefin;
        
    } 

    public Mariage(MariagePk mariagePk, Date datefin ,int nbrEnfant ) {
        this.mariagePk = mariagePk;
        this.nbrEnfant = nbrEnfant;
        this.datefin = datefin;
    }

   

    public Mariage(MariagePk mariagePk, Femme femme, Homme hommee) {
        this.mariagePk = mariagePk;
        this.femme = femme;
        this.hommee = hommee;
    }

    public Mariage(MariagePk mariagePk, Femme femme, Homme hommee, int nbrEnfant,  Date datefin) {
        this.mariagePk = mariagePk;
        this.femme = femme;
        this.hommee = hommee;
        this.nbrEnfant = nbrEnfant;
      
        this.datefin = datefin;
    }
    

    public MariagePk getMariagePk() {
        return mariagePk;
    }

    public void setMariagePk(MariagePk mariagePk) {
        this.mariagePk = mariagePk;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    public Homme getHomme() {
        return hommee;
    }

    public void setHomme(Homme homme) {
        this.hommee = homme;
    }

   

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Mariage{" + "mariagePk=" + mariagePk + ", femme=" + femme + ", homme=" + hommee +  ", datefin=" + datefin + '}';
    }
    
    
}
