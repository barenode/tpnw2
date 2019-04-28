package tpnw2.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_employee")
	private Integer id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="passwd")
	private String password;
	
	@Column(name="administrator")
	private Boolean administrator;
	
	@ManyToOne
	@JoinColumn(name="id_office", referencedColumnName="id_office", nullable=false)
	private OfficeEntity office;
	
	@ManyToMany(cascade = {CascadeType.MERGE})                  
    @JoinTable(name = "drives",
        joinColumns = @JoinColumn(name = "id_employee"),
        inverseJoinColumns = @JoinColumn(name = "id_car")
    )
	private List<CarEntity> cars;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	

	public Boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Boolean administrator) {
		this.administrator = administrator;
	}	

	public OfficeEntity getOffice() {
		return office;
	}

	public void setOffice(OfficeEntity office) {
		this.office = office;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}		
}
