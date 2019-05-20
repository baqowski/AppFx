package component.Hibernate.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import component.Hibernate.Entity.Cel;
import component.Hibernate.Entity.Miesiac;
import component.Hibernate.Entity.Produkt;
import component.Hibernate.HibernateUtils;
import javafx.collections.ObservableList;
import org.hibernate.HibernateException;


import javafx.collections.FXCollections;


public class CelDAO extends HibernateUtils {

	public static ObservableList<Cel> getCelPracownikList() {
		List<Object[]> listObject = null;
		List<Cel> listCel = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
					+ "JOIN cel.idMiesiac miesiac ");
			// Select list
			listObject = getCurrentSession().createQuery(query).list();
			for (Object[] o : listObject) {
				listCel.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(listCel);
			}

			// Commit the transaction
			getCurrentSession().getTransaction().commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return obsList;

	}
	
	public static List<Object[]> getCelPracownikForKwartalProdukt(Integer id, Integer id2, Integer id3, Integer idProdukt) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac "
				+ "WHERE (miesiac.id =: idM OR miesiac.id =: idM2 oR miesiac.id =:idM3 ) AND produkt.id =: idP ");
		
		List<Object[]> list = null;
		// List<Cel> celList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("idM", id).setParameter("idM2", id2).setParameter("idM3", id3)
					.setParameter("idP", idProdukt).list();
			
			getCurrentSession().getTransaction().commit();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static List<Object[]> getCelOddzialForKwartalProdukt(Integer id, Integer id2, Integer id3, Integer idProdukt) {

		String query = ("SELECT DISTINCT c.idProdukt, c.idMiesiac, SUM(c.wartosc), SUM(c.wynik), SUM(c.bilans)"
				+ " FROM Cel c " + "INNER JOIN c.idProdukt p " + "INNER JOIN c.idMiesiac m "
				+ "WHERE (m.id =: idM OR m.id =: idM2 oR m.id =: idM3) AND p.id =: idP " + "GROUP BY c.idProdukt, c.idMiesiac");
		List<Object[]> list = null;
		
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("idM", id).setParameter("idM2", id2).setParameter("idM3", id3)
					.setParameter("idP", idProdukt).list();
			
			getCurrentSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelPracownikForMiesiacProdukt(Integer idMiesiac, Integer idProdukt) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE miesiac.id =: idM  AND produkt.id =: idP ");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("idM", idMiesiac).setParameter("idP", idProdukt).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelForKwartalPracownik(Integer id, Integer id2, Integer id3, Integer idPracownik) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE (miesiac.id =: id1 OR miesiac.id =: id2 OR miesiac.id =: id3 ) AND pracownik.id =: id4");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id1", id).setParameter("id2", id2).
					setParameter("id3", id3).setParameter("id4", idPracownik).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static ObservableList<Cel> getCelForKwartal(Integer id, Integer id2, Integer id3) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE miesiac.id =: id  OR miesiac.id =: id2  OR miesiac.id =: id3 ");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id", id).setParameter("id2", id2)
					.setParameter("id3", id3).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static ObservableList<Cel> getCelForMiesiac(Integer idMiesiac) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE miesiac.id =: id ");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id", idMiesiac).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return null;
	}
	
	public static ObservableList<Cel> getCelForProduktPracownik(Integer idProdukt, Integer idPracownik) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE produkt.id =: id1   AND pracownik.id =: id2");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id1", idProdukt).setParameter("id2", idPracownik).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelForProdukt(Integer idProdukt) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE produkt.id =: id");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id", idProdukt).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	
	
	public static ObservableList<Cel> getCelForMiesiacPracownik(Integer idMiesiac, Integer idPracownik) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE miesiac.id =: id1   AND pracownik.id =: id2");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id1", idMiesiac).setParameter("id2", idPracownik).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelForMiesiacPracownikProdukt(Integer idMiesiac, Integer idPracownik, Integer idProdukt) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE miesiac.id =: id1  AND pracownik.id =: id2 AND produkt.id =: id3 ");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id1", idMiesiac).setParameter("id2", idPracownik)
					.setParameter("id3", idProdukt).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelForKwartalPracownikProdukt(Integer id, Integer id2, Integer id3, Integer idPracownik, Integer idProdukt) {
		
		String query = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE (miesiac.id =: id1 OR miesiac.id =: id2 OR miesiac.id =: id3 ) AND pracownik.id =: id4 AND produkt.id=: id5");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("id1", id).setParameter("id2", id2).
					setParameter("id3", id3).setParameter("id4", idPracownik).setParameter("id5", idProdukt).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static List<Object[]> getCelOddzialForMiesiacProdukt(Integer idMiesiac, Integer idProdukt) {
		

		String query = ("SELECT DISTINCT c.idProdukt, c.idMiesiac, SUM(c.wartosc), SUM(c.wynik), SUM(c.bilans) "
				+ "FROM Cel c " + "INNER JOIN c.idProdukt p " + "INNER JOIN c.idMiesiac m "
				+ "WHERE m.id =: idM  AND p.id =: idP "
				+ "GROUP BY c.idProdukt, c.idMiesiac");
		List<Object[]> list = null;
		
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query).setParameter("idM", idMiesiac).setParameter("idP", idProdukt).list();
			
			getCurrentSession().getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
	public static ObservableList<Cel> getCelOdzialList() {
		List<Cel> listCel = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;

		try {
			getCurrentSession().getTransaction().begin();

			String query = ("SELECT DISTINCT c.idProdukt, c.idMiesiac, SUM(c.wartosc), SUM(c.wynik), SUM(c.bilans)"
					+ " FROM Cel c " + "INNER JOIN c.idProdukt p " + "INNER JOIN c.idMiesiac m " + "INNER JOIN c.idPracownik pr "
					+ "GROUP BY c.idProdukt, c.idMiesiac");

			List<Object[]> list = getCurrentSession().createQuery(query).list();

			// Select list

			for (Object[] o : list) {
				Produkt p = (Produkt) o[0];
				Miesiac m = (Miesiac) o[1];
				//System.out.println("wartosc " + o[2] + " wynik " + o[3] + " bilans " + o[4]);
				// int wartosc = (int) o[2];
				// int wynik = (int) o[3];
				// int bilans = (int) o[4];
				Long wartosc = (Long) o[2];
				Long wynik = (Long) o[3];
				Long bilans = (Long) o[4];
				//System.out.println(wartosc);
				// System.out.println(wartosc + " " + wynik + " " + bilans);
				Cel c = new Cel();
				// c.setBilans((int) o[2]);
				c.setIdProdukt(p);
				c.setIdMiesiac(m);
				c.setWartosc(wartosc.intValue());
				c.setWynik(wynik.intValue());
				c.setBilans(bilans.intValue());
				// System.out.println(c.toString());
				listCel.add(c);
				obsList = FXCollections.observableArrayList(listCel);
				// listCel.add((Cel) o[1]);
				// obsList = FXCollections.observableArrayList(listCel);
				// System.out.println(produkt.getNameProdukt());
				// System.out.println(miesiac.getMiesiac());
				// System.out.println(Arrays.toString(arr));
				// System.out.println(p.toString() + m.toString());
				// System.out.println(o[1]);

			}

			// Commit the transaction
			getCurrentSession().getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();
		}
		return obsList;

	}
	
	public static boolean isCelExists(Cel cel) {
		//boolean result = false;
		org.hibernate.Transaction tx = null;
		try {
			// tx = session.beginTransaction();
			tx = getCurrentSession().getTransaction();
			tx.begin();
			Query query = getCurrentSession()
					.createQuery("from Cel c where c.idPracownik=" + cel.getIdPracownik().getId() + " AND c.idProdukt="
							+ cel.getIdProdukt().getId() + " AND c.idMiesiac=" + cel.getIdMiesiac().getId());

			Cel c = (Cel) ((org.hibernate.Query) query).uniqueResult();
			tx.commit();
			//System.out.println(result);

			if (c != null)
				return true;
		} catch (Exception ex) {
			if (tx != null) {
				tx.rollback();
				//return result;
			}
			ex.printStackTrace();
		} finally {
			getCurrentSession().close();
		}
		//return result;
		return false;
	}
	
	
	public static ObservableList<Cel> getCelForPracownik(Integer id) {

		String query2 = (" FROM Cel cel " + "JOIN cel.idPracownik pracownik " + "JOIN cel.idProdukt produkt "
				+ "JOIN cel.idMiesiac miesiac " + "WHERE pracownik.id =: id ");

		List<Object[]> list = new ArrayList<Object[]>();
		List<Cel> celList = new ArrayList<Cel>();
		ObservableList<Cel> obsList = null;
		try {
			getCurrentSession().getTransaction().begin();
			list = getCurrentSession().createQuery(query2).setParameter("id", id).list();
			for (Object[] o : list) {
				celList.add((Cel) o[0]);
				obsList = FXCollections.observableArrayList(celList);
			}
			System.out.println(list);
			getCurrentSession().getTransaction().commit();
			return obsList;

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			getCurrentSession().getTransaction().rollback();

		}
		return null;

	}
	
}
