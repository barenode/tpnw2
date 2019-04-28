package tpnw2.domain;

public class Company extends Client {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private Contract contract;
	
	public Company() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@Override
	public String toString() {
		return name;
	}
}
