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

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tpnw2.domain.Employee;
import tpnw2.domain.Office;
import tpnw2.domain.OrderStatus;
import tpnw2.report.Dataset;
import tpnw2.report.Record;
import tpnw2.view.component.EnumModel;

@Repository
@Transactional
public class ReportDaoImpl implements ReportDao {

	@Autowired
	private DataSource ds;
	
	public ReportDaoImpl() {
		super();
	}
	

	@Override
	public Dataset<Record> orderRatio(Office o, Employee driver) {
		List<Record> records = Arrays.asList(
			orderRatio(o, driver, OrderStatus.Filled),
			orderRatio(o, driver, OrderStatus.Cancelled)
		);
		return new Dataset<>(records, null, null, null);
	}
	
	private Record orderRatio(Office o, Employee driver, OrderStatus status) {
		try {
			List<Record> records = new CursorStoredProcedureTemplate<Record, Void>(ds) {
	
				@Override
				protected List<Record> createResultList() {
					return new ArrayList<>();
				}
	
				@Override
				protected Record buildResult(ResultSet resultSet) throws Exception {
					return new RatioRecordImpl(
						new EnumModel<OrderStatus>(Model.of(status)).getObject(),
						resultSet.getInt(1)							
					);
				}
	
				@Override
				protected String getQueryString() {					
					if (driver!=null) {
						return "{call sp_orderRatioDriver (?, ?, ?, ?)}";
					} else if (o!=null) {
						return "{call sp_orderRatioOffice (?, ?, ?, ?)}";
					} else {
						return "{call sp_orderRatio (?, ?, ?)}";
					}					
				}
	
				@Override
				protected void setParameters(CallableStatement statement, Void args) throws Exception {
					statement.setInt(1, LocalDate.now().getYear());
					statement.setInt(2, LocalDate.now().getMonthValue());
					statement.setString(3, status.getCode());
					if (driver!=null) {
						statement.setInt(4, driver.getId());						
					} else if (o!=null) {
						statement.setInt(4, o.getId());						
					}		
				}			
			}.execute(null);
			return records.get(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Dataset<Record> orderCount(Office o) {
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
					if (o==null) {
						return "{call sp_selectTotalMonthOrdersPerDriver (?, ?)}";
					} else {
						return "{call sp_selectTotalMonthOrdersPerDriverOffice (?, ?, ?)}";
					}					
				}
	
				@Override
				protected void setParameters(CallableStatement statement, Void args) throws Exception {
					statement.setInt(1, LocalDate.now().getYear());
					statement.setInt(2, LocalDate.now().getMonthValue());
					if (o!=null) {
						statement.setInt(3, o.getId());						
					}
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
	
	private static final class RatioRecordImpl implements Record {

		private final String name;
		private final int orders;
		
		public RatioRecordImpl(String name, int orders) {
			super();
			this.name = name;
			this.orders = orders;
		}
		
		@Override
		public Map<String, String> map() {
			Map<String, String> map = new LinkedHashMap<>();
			map.put("label", format(name));
			map.put("value", format(orders));
			return map;
		}		
	}
}
