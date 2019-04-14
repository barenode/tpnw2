package tpnw2.domain;

public class Company extends Client {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public Company() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
