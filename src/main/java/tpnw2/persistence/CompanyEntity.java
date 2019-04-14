package tpnw2.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class CompanyEntity extends ClientEntity {

	@Column(name="name")
	private String name;
	
	public CompanyEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
