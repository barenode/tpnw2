package tpnw2.report.persistence;

import tpnw2.domain.Employee;
import tpnw2.domain.Office;
import tpnw2.report.Dataset;
import tpnw2.report.Record;

public interface ReportDao {

	Dataset<Record> orderCount(Office o);
	
	Dataset<Record> orderRatio(Office o, Employee driver);
}
