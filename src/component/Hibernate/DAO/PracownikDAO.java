package component.Hibernate.DAO;
import java.util.List;
import javax.persistence.Query;

import component.Hibernate.Entity.Pracownik;
import component.Hibernate.HibernateUtils;
import org.hibernate.HibernateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PracownikDAO extends HibernateUtils {



	public static ObservableList<Pracownik> getPracownikList() {
		
		//Session session = HibernateUtils.getInstance().getCurrentSession();	
		List<Pracownik> list = null;
		ObservableList<Pracownik> obsList = null;
		try {

			getCurrentSession().getTransaction().begin();
			// Select list
			list = getCurrentSession().createQuery("from Pracownik").list();
			obsList = FXCollections.observableArrayList(list);
			// Commit the transaction
			getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return obsList;

	}

	public static boolean isPracownikExists(Pracownik p){

		try {
			getCurrentSession().beginTransaction();

			//String hql = "from Stock s where s.stockCode = :stockCode";
			//List result = session.createQuery(hql)
				//	.setString("stockCode", "7277")
				//	.list();

			Query query = getCurrentSession().createQuery("SELECT COUNT(p) FROM Pracownik p WHERE p.imie = :name AND p.nazwisko = :surname ")
					.setString("name", p.getImie()).setString("surname", p.getNazwisko());
			Long count = (Long) query.getSingleResult();
			System.out.println("Licznik " + count);
			getCurrentSession().getTransaction().commit();
			//getSession().close();
			if (count > 0) {
				return true;
			}
	
		} catch (HibernateException e) {
			e.printStackTrace();
			getCurrentSession().getTransaction().rollback();
		}
		return false;
	}
}
