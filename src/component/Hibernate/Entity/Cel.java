package component.Hibernate.Entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;


@Entity(name = "Cel")
@Table(name = "Cel", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Cel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	private int id;

	// @Column(name="idPracownik")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPracownik", referencedColumnName = "id", nullable = false, unique = true)
	private Pracownik idPracownik;

	// @Column(name="idProdukt")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProdukt", referencedColumnName = "id", nullable = false, unique = true)
	private Produkt idProdukt;

	// @Column(name="idMiesiac")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMiesiac", referencedColumnName = "id", nullable = false, unique = true)
	private Miesiac idMiesiac;

	@Column(name = "wartosc_oczekiwana")
	private int wartosc;

	@Column(name = "wartosc_osiagnieta")
	private int wynik;

	@Column(name = "bilans")
	private int bilans;

	public Cel() {
	}

	public Cel(Pracownik idPracownik, Produkt idProdukt) {
		super();
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;

	}

	public Cel(Pracownik idPracownik, Produkt idProdukt, int bilans) {
		super();
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;
		this.bilans = bilans;
	}

	public Cel(int idPracownik, int idProdukt, int idMiesiac, int wartosc, int wynik) {
		super();
		this.wartosc = wartosc;
		this.wynik = wynik;
	}

	public Cel(int id, Pracownik idPracownik, Produkt idProdukt, Miesiac idMiesiac, int wartosc, int wynik) {
		super();
		this.id = id;
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;
		this.idMiesiac = idMiesiac;
		this.wartosc = wartosc;
		this.wynik = wynik;
		this.bilans = wynik - wartosc;
	}

	public Cel(Pracownik idPracownik, Produkt idProdukt, Miesiac idMiesiac) {
		super();
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;
		this.idMiesiac = idMiesiac;
		this.bilans = wynik - wartosc;

	}

	public Cel(Pracownik idPracownik, Produkt idProdukt, Miesiac idMiesiac, Integer wartosc, Integer wynik) {
		super();
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;
		this.idMiesiac = idMiesiac;
		this.wartosc = wartosc;
		this.wynik = wynik;
		this.bilans = wynik - wartosc;

	}

	public Cel(Pracownik idPracownik, Produkt idProdukt, Miesiac idMiesiac, Integer wartosc, Integer wynik,
			Integer bilans) {
		super();
		this.idPracownik = idPracownik;
		this.idProdukt = idProdukt;
		this.idMiesiac = idMiesiac;
		this.wartosc = wartosc;
		this.wynik = wynik;
		this.bilans = wynik - wartosc;
	}

	//public IntegerProperty getSumWartoscProperty() {

	//	return new SimpleIntegerProperty(this.wartosc);
	//}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWartosc() {
		return wartosc;
	}

	public void setWartosc(int wartosc) {
		this.wartosc = wartosc;
	}

	public int getWynik() {

		return wynik;
	}

	public void setWynik(int wynik) {
		this.wynik = wynik;
	}

	public Pracownik getIdPracownik() {
		return idPracownik;
	}

	public void setIdPracownik(Pracownik idPracownik) {
		this.idPracownik = idPracownik;
	}

	public Produkt getIdProdukt() {
		return idProdukt;
	}

	public void setIdProdukt(Produkt idProdukt) {
		this.idProdukt = idProdukt;
	}

	public Miesiac getIdMiesiac() {
		return idMiesiac;
	}

	public void setIdMiesiac(Miesiac idMiesiac) {
		this.idMiesiac = idMiesiac;
	}

	public Integer getBilans() {
		return bilans;
	}

	public void setBilans(int bilans) {
		this.bilans = bilans;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bilans;
		result = prime * result + id;
		result = prime * result + ((idMiesiac == null) ? 0 : idMiesiac.hashCode());
		result = prime * result + ((idPracownik == null) ? 0 : idPracownik.hashCode());
		result = prime * result + ((idProdukt == null) ? 0 : idProdukt.hashCode());
		result = prime * result + wartosc;
		result = prime * result + wynik;
		return result;
	}



	@Override
	public String toString() {
		return  idPracownik + " " + idProdukt + " " + idMiesiac;
	}

}
