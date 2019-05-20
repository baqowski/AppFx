package component.Hibernate.DAO;
import javax.persistence.Query;
import component.Hibernate.HibernateUtils;


public class Operations extends HibernateUtils {


	
	public static void deleteByID(Class className, Integer id) {
		try {
			getCurrentSession().getTransaction().begin();
			String hql = "delete " + className.getName() + " where id = :id";
			Query q = getCurrentSession().createQuery(hql).setParameter("id", id);
			q.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// // Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		} finally {
			getCurrentSession().close();
		}
	}

	public static void updateByID(Class className, Integer id) {
		try {
			Query query = getCurrentSession().createSQLQuery("UPDATE " + className.getName() + " WHERE id=:id");
			query.setParameter("id", id);

		} catch (Exception e) {
			e.printStackTrace();
			// // Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		} finally {
			getCurrentSession().close();
		}
	}

	
}
