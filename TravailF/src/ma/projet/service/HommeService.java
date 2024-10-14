/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.Date;
import java.util.List;
import ma.projet.beans.Femme;
import ma.projet.beans.Homme;
import ma.projet.beans.Mariage;
import ma.projet.dao.Idao;
import ma.projet.util.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author OUMAIMA
 */
public class HommeService implements Idao< Homme> {
   
    @Override
    public boolean create(Homme o) {
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
    public Homme getById(int id) {
        Homme hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = (Homme) session.get(Homme.class, id);
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
        return hommes;
    }

    @Override
    public List<Homme> getAll() {
        List<Homme> hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createQuery("from Homme").list();
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
        return hommes;
    }

    public List<Femme> getEpousesBetweenDates(Homme homme, Date d1, Date d2) {
        List<Femme> epouse = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("getEpousesBetweenDates");
            query.setParameter("hommeId", homme.getId());
            query.setParameter("d1", d1);
            query.setParameter("d2", d2);
            epouse = query.list();
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

        return epouse;

    }

    public List<Mariage> getMariagesHommeAvecDetails(Homme homme) {
        List<Mariage> mariages = null;
        Session session = null;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Mariage.class, "mariage");
            criteria.createAlias("mariage.femme", "femme");
            criteria.add(Restrictions.eq("femme.homme", homme));
            criteria.addOrder(Order.desc("mariage.dateFin"));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

            mariages = criteria.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return mariages;
    }

    public static void getEpousesParHomme(Homme homme) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(
                    "SELECT m.femme FROM Mariage m WHERE m.homme.id = :hommeId"
            );
            query.setParameter("hommeId", homme.getId());

            List<Femme> femmes = query.list();

            transaction.commit();

            if (!femmes.isEmpty()) {
                System.out.println("Épouses de l'homme avec l'ID " + homme.getId() + ":");
                for (Femme wife : femmes) {
                    System.out.println(wife.getNom() + " " + wife.getPrenon());
                }
            } else {
                System.out.println("Aucune épouse trouvée pour l'homme avec l'ID " + homme.getId());
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void getHommesMariesParQuatreFemmesEntreDates(Date startDate, Date endDate) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Homme.class, "h");
        criteria.createAlias("h.mariages", "m", JoinType.LEFT_OUTER_JOIN);

        criteria.add(Restrictions.between("m.dateFin", startDate, endDate));
        criteria.setProjection(Projections.groupProperty("h.id"));

        List<Integer> hommeIds = criteria.list();

        for (Integer hommeId : hommeIds) {
            int marriageCount = countMarriagesForHomme(hommeId, startDate, endDate);
            if (marriageCount == 4) {
                System.out.println("Homme ID: " + hommeId + " is married by 4 women");
            }
        }

        session.close();
    }

    private int countMarriagesForHomme(int hommeId, Date startDate, Date endDate) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Mariage.class, "m");

        criteria.add(Restrictions.eq("m.homme.id", hommeId));
        criteria.add(Restrictions.between("m.dateFin", startDate, endDate));

        return ((Number) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    public int countMenMarriedByFourWomenBetweenDates(Date startDate, Date endDate) {
        Session session = null;
        int count = 0;

        try {
            session = NewHibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Mariage.class)
                    .createAlias("homme", "h")
                    .add(Restrictions.lt("dateFin", endDate))
                    .add(Restrictions.gt("dateDebut", startDate));

            List<Mariage> mariages = criteria.list();

            for (Mariage mariage : mariages) {

                Homme homme = mariage.getHomme();

                if (homme.getMariage().size() >= 4) {
                    count++;
                }
            }
            count = count / 4;
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return (count);
    }

    public List<Mariage> getMariagesByHomme(Homme homme) {
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Mariage> mariages = session.createCriteria(Mariage.class)
                .add(Restrictions.eq("homme", homme))
                .list();

        session.getTransaction().commit();
        session.close();

        return mariages;
    }
}
