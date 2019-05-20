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
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "Pracownik", uniqueConstraints = { @UniqueConstraint(columnNames = { "id" }) })
public class Pracownik implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	private Integer id;

	@Column(name = "imie")
	private String imie;

	@Column(name = "nazwisko")
	private String nazwisko;

	
	@OneToMany(mappedBy = "idPracownik")
	private List<Cel> cel;

	
	
	public Pracownik() {
		super();
		//this.imieProperty = new SimpleStringProperty((String) this.imie);
		//this.nazwiskoProperty = new SimpleStringProperty((String) this.nazwisko);
		
		
		
	}
	
	public Pracownik(String imie, String nazwisko) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		//this.imieProperty = new SimpleStringProperty((String) imie);
		//this.nazwiskoProperty = new SimpleStringProperty((String) nazwisko);
	}
	
	public Pracownik(int id, String imie, String nazwisko) {
		super();
		this.id = id;
		this.imie = imie;
		this.nazwisko = nazwisko;
		//this.imieProperty = new SimpleStringProperty((String) imie);
		//this.nazwiskoProperty = new SimpleStringProperty((String) nazwisko);
	}

	public Pracownik(String imie, String nazwisko, List<Cel> pracownik) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.cel = pracownik;
		//this.imieProperty = new SimpleStringProperty((String) imie);
		//this.nazwiskoProperty = new SimpleStringProperty((String) nazwisko);
	}


	
	//public StringProperty getImieProperty() {
	//	return imieProperty;
	//}
	


	//public StringProperty getNazwiskoProperty() {
	//	return nazwiskoProperty;
	//}
	
	

	public Integer getId() {
		return id;
	}

	public List<Cel> getCel() {
		return cel;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCel(List<Cel> cel) {
		this.cel = cel;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<Cel> getList() {
		return cel;
	}

	public void setList(List<Cel> pracownik) {
		this.cel = pracownik;
	}

	@Override
	public String toString() {
		return  imie + " " + nazwisko + " "  ;
	}
	
	
	
	public void addToDatabase() {
		
	}


}
