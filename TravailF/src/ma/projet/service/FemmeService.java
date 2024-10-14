/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.Date;
import java.util.List;
import ma.projet.beans.Femme;
import ma.projet.dao.Idao;
import ma.projet.util.NewHibernateUtil;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author OUMAIMA
 */
public class FemmeService implements Idao<Femme>{
     @Override
    public boolean create(Femme o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public Femme getById(int id) {
        Femme femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = (Femme) session.get(Femme.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return femmes;
    }

    @Override
    public List<Femme> getAll() {
        List<Femme> femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createQuery("from Femme").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return femmes;
    }

    public int getNombreEnfantsEntreDates(int femmeId, Date dateDebut, Date dateFin) {
        Session session = null;
        Transaction tx = null;
        int nombreEnfants = 0;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Query query = session.createQuery("SELECT SUM(m.nbrEnfant) FROM Mariage m WHERE m.femme.id = :femmeId AND m.dateFin BETWEEN :dateDebut AND :dateFin");
            query.setParameter("femmeId", femmeId);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);

            Object result = query.uniqueResult();
            if (result != null) {
                nombreEnfants = ((Number) result).intValue();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return nombreEnfants;
    }

    public Femme getFemmeLaPlusAgee() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Femme femmeLaPlusAgee = null;

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Femme ORDER BY dateNaissance ASC");
            query.setMaxResults(1);

            femmeLaPlusAgee = (Femme) query.uniqueResult();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return femmeLaPlusAgee;
    }

    public List<Femme> getFemmesMarieesDeuxFoisOuPlus() {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Femme> femmes = null;

        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT f "
                    + "FROM Femme f "
                    + "WHERE f IN ("
                    + "   SELECT m.femme "
                    + "   FROM Mariage m "
                    + "   GROUP BY m.femme "
                    + "   HAVING COUNT(m.femme) >= 2"
                    + ")");
            femmes = query.list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return femmes;
    }

}
