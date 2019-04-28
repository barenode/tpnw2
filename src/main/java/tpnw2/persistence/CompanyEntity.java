package tpnw2.persistence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class CompanyEntity extends ClientEntity {

	@Column(name="name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_client", referencedColumnName="id_client", nullable=false)
	private List<ContractEntity> contracts;
	
	public CompanyEntity() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ContractEntity> getContracts() {
		return contracts;
	}

	public void setContracts(List<ContractEntity> contracts) {
		this.contracts = contracts;
	}
}
