package tpnw2.report.persistence;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

public class ReportDaoImpl implements ReportDao {

	@Autowired
	private DataSource ds;
	
	public ReportDaoImpl() {
		super();
	}
}
