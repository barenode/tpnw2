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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return numberplate;
	}	
}
