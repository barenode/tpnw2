package tpnw2.domain;

import java.io.Serializable;

public class OrderCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	/**
	 * Either administrator or driver.
	 */
	private Employee employee;	
	private Office office;
	private Client client;
	
	public OrderCriteria() {
		super();
	}
	
	public OrderCriteria(Employee employee) {
		super();
		this.employee = employee;
	}
	
	public OrderCriteria(Office office) {
		super();
		this.office = office;
	}
	
	public OrderCriteria(Client client) {
		super();
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}		
}
