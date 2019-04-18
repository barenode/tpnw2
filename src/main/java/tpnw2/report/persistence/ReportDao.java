package tpnw2.report.persistence;

import tpnw2.report.Dataset;
import tpnw2.report.Record;
import tpnw2.report.SVTimeRecord;
import tpnw2.report.TimeDataset;

public interface ReportDao {

	Dataset<Record> dataset();
}
