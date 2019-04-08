package tpnw2.domain;

import java.io.Serializable;

public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String numberplate;
	private Employee owner;
	
	public Car() {
		super();
	}
	
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
	
	public Employee getOwner() {
		return owner;
	}
	
	public void setOwner(Employee owner) {
		this.owner = owner;
	}	
}
