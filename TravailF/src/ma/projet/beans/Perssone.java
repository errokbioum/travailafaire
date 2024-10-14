/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 *
 * @author OUMAIMA
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED)
public class Perssone {
     @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    protected int id;
    protected String nom;
    protected String prenon;
    protected String telephone;
    protected String adress;
    @Temporal((TemporalType.DATE) )
    protected Date dateNaic ;

    public Perssone(String nom, String prenon, String telephone, String adress, Date dateNaic) {
        this.nom = nom;
        this.prenon = prenon;
        this.telephone = telephone;
        this.adress = adress;
        this.dateNaic = dateNaic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenon() {
        return prenon;
    }

    public void setPrenon(String prenon) {
        this.prenon = prenon;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getDateNaic() {
        return dateNaic;
    }

    public void setDateNaic(Date dateNaic) {
        this.dateNaic = dateNaic;
    }

    @Override
    public String toString() {
        return "Perssone{" + "id=" + id + ", nom=" + nom + ", prenon=" + prenon + ", telephone=" + telephone + ", adress=" + adress + ", dateNaic=" + dateNaic + '}';
    }
    
    
}
