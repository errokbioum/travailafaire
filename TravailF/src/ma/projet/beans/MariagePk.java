/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author OUMAIMA
 */

public class MariagePk implements Serializable {
    

    
 private int homme;
 private int femme;
  private Date datedebut;

    public MariagePk(int homme, int femme, Date datedebut) {
        this.homme = homme;
        this.femme = femme;
        this.datedebut = datedebut;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public MariagePk(int homme, int femme) {
        this.homme = homme;
        this.femme = femme;
    }

    public int getHomme() {
        return homme;
    }

    public void setHomme(int homme) {
        this.homme = homme;
    }

    public int getFemme() {
        return femme;
    }

    public void setFemme(int femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "MariagePk{" + "homme=" + homme + ", femme=" + femme + '}';
    }
 
 
}