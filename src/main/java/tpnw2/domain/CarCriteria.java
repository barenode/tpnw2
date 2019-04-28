package tpnw2.domain;

import java.io.Serializable;

public class CarCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String numberplate;
	private Employee owner;
	
	public CarCriteria() {
		super();
	}
	
	public CarCriteria(String numberplate) {
		super();
		this.numberplate = numberplate;
	}

	public String getNumberplate() {
		return numberplate;
	}

	public void setNumberplate(String numberplate) {
		this.numberplate = numberplate;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}
}
