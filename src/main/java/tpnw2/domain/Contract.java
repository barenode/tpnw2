package tpnw2.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private BigDecimal pricePerKm;
	
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
