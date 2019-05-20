package component.Hibernate.Entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import component.Hibernate.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;



@Entity(name = "Produkt")
@Table(name = "Produkt", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Produkt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "nazwa")
	private String nameProdukt;

	
	@OneToMany(mappedBy = "idProdukt")
	private List<Cel> list;

	

	public Produkt() {
		super();
	}

	public Produkt(String nameProdukt) {
		super();
		this.nameProdukt = nameProdukt;
	}


	public Produkt(String nameProdukt, List<Cel> produkt) {
		super();
		this.nameProdukt = nameProdukt;
		this.list = produkt;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameProdukt() {
		return nameProdukt;
	}

	public void setNameProdukt(String nameProdukt) {
		this.nameProdukt = nameProdukt;
	}

	public List<Cel> getList() {
		return list;
	}

	public void setList(List<Cel> produkt) {
		this.list = produkt;
	}


	

	public void addToDatabase(Produkt p) {

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.getTransaction().begin();
		try {

			// Produkt p = new Produkt("Pożyczka");
			// Save the produkt in database
			// session.save(new Produkt(nazwaProduktu));
			session.save(p);

			// Commit the transaction
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();

		}

	}

	@Override
	public String toString() {
		return nameProdukt ;
	}

}
