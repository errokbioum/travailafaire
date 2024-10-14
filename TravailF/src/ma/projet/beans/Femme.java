/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 *
 * @author OUMAIMA
 */
@Entity
public class Femme  extends Perssone{
    @OneToMany( mappedBy = "femme" , fetch = FetchType.EAGER)
    List<Mariage > mariage = null;
    

    public Femme(String nom, String prenon, String telephone, String adress, Date dateNaic) {
        super(nom, prenon, telephone, adress, dateNaic);
    }

    public List<Mariage> getMariage() {
        return mariage;
    }

    public void setMariage(List<Mariage> mariage) {
        this.mariage = mariage;
    }
    

   
    
    
    
}
