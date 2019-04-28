package tpnw2.domain;

import java.io.Serializable;

public class EmployeeCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String lastname;
	private Office office;
	
	public EmployeeCriteria() {
		super();
	}
	
	public EmployeeCriteria(String email) {
		super();
		this.email = email;
	}
	
	public EmployeeCriteria(Office office) {
		super();
		this.office = office;
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

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}	
}
