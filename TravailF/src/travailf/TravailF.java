/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travailf;

import java.util.Date;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.service.FemmeService;
import ma.projet.service.HommeService;
import ma.projet.service.MariageService;
import ma.projet.util.NewHibernateUtil;
import ma.projet.beans.MariagePk;

/**
 *
 * @author OUMAIMA
 */
public class TravailF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NewHibernateUtil.getSessionFactory();
         FemmeService fs = new FemmeService();
        HommeService hs = new HommeService();
        MariageService ms = new MariageService();
        //1==========================
        Femme f1 = new Femme("Chafika", "Wahbi", "069635805368", "Casablanca", new Date("2001/02/03"));
        Femme f2 = new Femme("Amina", "Smith", "065432187965", "Marrakech", new Date("1995/04/15"));
        Femme f3 = new Femme("Leila", "Johnson", "062345678912", "Rabat", new Date("1990/07/22"));
        Femme f4 = new Femme("Karima", "Williams", "067890123456", "Tangier", new Date("1998/09/10"));
        Femme f5 = new Femme("Lina", "Brown", "063454353423", "Agadir", new Date("1993/11/30"));
        Femme f6 = new Femme("Nadia", "Davis", "068765432189", "Fes", new Date("1988/01/25"));
        Femme f7 = new Femme("Yasmine", "Jones", "065432188765", "Tetouan", new Date("1996/06/07"));
        Femme f8 = new Femme("Sara", "Martin", "061234569876", "Meknes", new Date("1994/03/18"));
        Femme f9 = new Femme("Houda", "Adams", "065432126787", "Oujda", new Date("1999/08/12"));
        Femme f10 = new Femme("Ines", "White", "064567890123", "Kenitra", new Date("1986/12/04"));

        Homme h1 = new Homme("Mohammed", "Fezzazi", "0612345678", "Casablanca", new Date("1990/05/15"));
        Homme h2 = new Homme("Ahmed", "Bouhaddi", "0654321098", "Marrakech", new Date("1985/10/20"));
        Homme h3 = new Homme("Khalid", "Cherkaoui", "0678901234", "Rabat", new Date("1992/03/30"));
        Homme h4 = new Homme("Karim", "El Kadiri", "0612345678", "Tangier", new Date("1988/09/25"));
        Homme h5 = new Homme("Youssef", "Moussaoui", "0645678901", "Agadir", new Date("1994/06/07"));

        fs.create(f1);
        fs.create(f2);
        fs.create(f3);
        fs.create(f4);
        fs.create(f5);
        fs.create(f6);
        fs.create(f7);
        fs.create(f8);
        fs.create(f9);
        fs.create(f10);

        hs.create(h1);
        hs.create(h2);
        hs.create(h3);
        hs.create(h4);
        hs.create(h5);

        MariagePk mpk1 = new MariagePk (1, 12, new Date("2023/03/09"));
        MariagePk mpk2 = new MariagePk(5, 13, new Date("2020/06/07"));
        MariagePk mpk3 = new MariagePk (6, 14, new Date("2021/03/09"));
        MariagePk mpk4 = new MariagePk (2, 11, new Date("2016/06/07"));
        MariagePk mpk5 = new MariagePk (4, 11, new Date("2022/06/07"));
        MariagePk mpk6 = new MariagePk (7, 11, new Date("2018/06/07"));
        MariagePk mpk7 = new   MariagePk(9, 11, new Date("2017/06/07"));

        Mariage m1 = new Mariage(mpk1, null, 3);
        Mariage m2 = new Mariage(mpk2, new Date("2023/06/11"), 2);
        Mariage m3 = new Mariage(mpk3, null, 3);
        Mariage m4 = new Mariage(mpk4, new Date("2019/08/12"), 1);
        Mariage m5 = new Mariage(mpk5, new Date("2023/01/01"), 3);
        Mariage m6 = new Mariage(mpk6, new Date("2020/06/01"), 3);
        Mariage m7 = new Mariage(mpk7, new Date("2018/04/15"), 3);

        ms.create(m1);
        ms.create(m2);
        ms.create(m3);
        ms.create(m4);
        ms.create(m5);
        ms.create(m6);
        ms.create(m7);

//2================
        for (Femme ff : fs.getAll()) {
            System.out.println(ff);
        }
//3=================
       

    }
    //8=========================
     
}
