package tpnw2.domain;

import java.io.Serializable;

public class EmployeeCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String lastname;

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
