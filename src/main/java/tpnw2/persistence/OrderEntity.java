package tpnw2.persistence;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="order_table")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_order")
	private Integer id;
	
	@Column(name="status")
	private String status;
	
	@Column(name="departure_address")
	private String departureAddress;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="departure_date")
	private Date departureDate;
	
	@Column(name="destination_address")
	private String destinationAddress;	
	
	@ManyToOne
	@JoinColumn(name="id_office", referencedColumnName="id_office", nullable=true)
	private OfficeEntity office;
	
	@ManyToOne
	@JoinColumn(name="id_administrator", referencedColumnName="id_employee", nullable=true)
	private EmployeeEntity administrator;
	
	@ManyToOne
	@JoinColumn(name="id_driver", referencedColumnName="id_employee", nullable=true)
	private EmployeeEntity driver;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="id_client", referencedColumnName="id_client", nullable=true)
	private ClientEntity client;
	
	public OrderEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}		

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public OfficeEntity getOffice() {
		return office;
	}

	public void setOffice(OfficeEntity office) {
		this.office = office;
	}

	public EmployeeEntity getAdministrator() {
		return administrator;
	}

	public void setAdministrator(EmployeeEntity administrator) {
		this.administrator = administrator;
	}

	public EmployeeEntity getDriver() {
		return driver;
	}

	public void setDriver(EmployeeEntity driver) {
		this.driver = driver;
	}

	public ClientEntity getClient() {
		return client;
	}

	public void setClient(ClientEntity client) {
		this.client = client;
	}	
}
