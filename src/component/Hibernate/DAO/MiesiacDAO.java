package component.Hibernate.DAO;

import java.util.List;

import component.Hibernate.Entity.Miesiac;
import component.Hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MiesiacDAO {
	
	public static ObservableList<Miesiac> getMiesiacList() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		List<Miesiac> list = null;
		ObservableList<Miesiac> obsList = null;
		try {

			session.getTransaction().begin();
			// Select list
			list = session.createQuery("from Miesiac").list();
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

}
