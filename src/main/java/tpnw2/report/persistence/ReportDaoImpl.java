package tpnw2.report.persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.report.Dataset;
import tpnw2.report.Record;

@Repository
@Transactional
public class ReportDaoImpl implements ReportDao {

	@Autowired
	private DataSource ds;
	
	public ReportDaoImpl() {
		super();
	}

	@Override
	public Dataset<Record> dataset() {
		try {
			List<Record> records = new CursorStoredProcedureTemplate<Record, Void>(ds) {
	
				@Override
				protected List<Record> createResultList() {
					return new ArrayList<>();
				}
	
				@Override
				protected Record buildResult(ResultSet resultSet) throws Exception {
					return new RecordImpl(
						resultSet.getString(2) + " " + resultSet.getString(3),
						resultSet.getInt(4)							
					);
				}
	
				@Override
				protected String getQueryString() {
					return "{call sp_selectTotalMonthOrdersPerDriver (?, ?)}";
				}
	
				@Override
				protected void setParameters(CallableStatement statement, Void args) throws Exception {
					statement.setInt(1, LocalDate.now().getYear());
					statement.setInt(2, LocalDate.now().getMonthValue());
				}			
			}.execute(null);
			return new Dataset<>(records, "name", Arrays.asList("orders"), Arrays.asList("Počet jízd"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private static final class RecordImpl implements Record {

		private final String name;
		private final int orders;
		
		public RecordImpl(String name, int orders) {
			super();
			this.name = name;
			this.orders = orders;
		}
		
		@Override
		public Map<String, String> map() {
			Map<String, String> map = new LinkedHashMap<>();
			map.put("name", format(name));
			map.put("orders", format(orders));
			return map;
		}		
	}
}
