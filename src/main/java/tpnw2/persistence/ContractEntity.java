package tpnw2.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contract")
public class ContractEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_contract")
	private Integer id;
	
	@Column(name="price_per_km_in_czk")
	private BigDecimal pricePerKm;
	
	public ContractEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPricePerKm() {
		return pricePerKm;
	}

	public void setPricePerKm(BigDecimal pricePerKm) {
		this.pricePerKm = pricePerKm;
	}	
}
