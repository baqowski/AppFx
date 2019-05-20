package component.Hibernate.DAO;

import java.util.List;

import javax.persistence.Query;

import component.Hibernate.Entity.Produkt;
import component.Hibernate.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProduktDAO {

	public static ObservableList<Produkt> getProduktList() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		List<Produkt> list = null;
		ObservableList<Produkt> obsList = null;
		try {

			session.getTransaction().begin();
			// Select list
			list = session.createQuery("from Produkt").list();
			obsList = FXCollections.observableArrayList(list);
			// Commit the transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		}
		return obsList;

	}
	
	public static boolean isProduktExists(Produkt p) {

		Session session = HibernateUtils.getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery("SELECT COUNT(p) FROM Produkt p WHERE p.nameProdukt = : name  ")
					.setString("name", p.getNameProdukt());
			Long count = (Long) query.getSingleResult();
			session.getTransaction().commit();
			
			System.out.println("Licznik " + count);
			if (count > 0) {
				//session.close();
				return true;
				
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
			//session.close();
			session.getTransaction().rollback();
		}
		return false;
	}
}
