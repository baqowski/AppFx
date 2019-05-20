package component.Hibernate.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "Miesiac")
@Table(name = "Miesiac", uniqueConstraints = {@UniqueConstraint(columnNames = { "id" }) })
public class Miesiac {

	@Id
	@GenericGenerator(name = "generator", strategy = "increment")
	@GeneratedValue(generator = "generator")
	@Column(name = "id")
	private Integer id;	
	
	@Column(name = "nazwa")
	private String miesiac;
	
	
	@OneToMany(mappedBy = "idMiesiac")
	private List<Cel> cel;

	
	
	public Miesiac(int id, String miesiac, List<Cel> cel) {
		super();
		this.id = id;
		this.miesiac = miesiac;
		this.cel = cel;
	}

	public Miesiac(String miesiac, List<Cel> cel) {
		super();
		this.miesiac = miesiac;
		this.cel = cel;
	}

	public Miesiac(String miesiac) {
		super();
		this.miesiac = miesiac;
	}

	public Miesiac() {
		super();
	}

	
	
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

	public String getMiesiac() {
		return miesiac;
	}

	public void setMiesiac(String miesiac) {
		this.miesiac = miesiac;
	}

	@Override
	public String toString() {
		return  miesiac;
	}
	
	
	
}
