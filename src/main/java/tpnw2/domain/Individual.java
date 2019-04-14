package tpnw2.domain;

public class Individual extends Client {
	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;
	
	public Individual() {
		super();
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
}
