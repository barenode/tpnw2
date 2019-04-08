package tpnw2.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_car")
	private Integer id;
	
	@Column(name="numberplate")
	private String numberplate;
	
	@ManyToOne
	@JoinColumn(name="id_owner", referencedColumnName="id_employee", nullable=false)
	private EmployeeEntity owner;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumberplate() {
		return numberplate;
	}

	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}

	public EmployeeEntity getOwner() {
		return owner;
	}

	public void setOwner(EmployeeEntity owner) {
		this.owner = owner;
	}	
}
