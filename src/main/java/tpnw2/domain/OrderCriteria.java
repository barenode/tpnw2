package tpnw2.domain;

import java.io.Serializable;

public class OrderCriteria implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	public OrderCriteria() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
