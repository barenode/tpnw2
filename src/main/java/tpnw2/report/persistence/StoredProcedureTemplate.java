package tpnw2.report.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

public abstract class StoredProcedureTemplate<V, K extends StoredProcedureArgs> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final DataSource dataSource;	
	
	public StoredProcedureTemplate(
		DataSource dataSource) 
	{
		super();
		this.dataSource = dataSource;	
	}
	
	/**
	 * Returns a query String in stored procedure format.
	 * Format: {call procedure_name(?, ?)}
	 * 
	 * @return A query string.
	 */
	protected abstract String getQueryString();	
	
	/**
	 * Sets parameters from arguments to statement
	 * 
	 * @param statement
	 * @param args
	 */
	protected abstract void setParameters(CallableStatement statement, K args) throws Exception;	

	
	/**
	 * Handle statement.
	 */
	protected abstract V doExecute(CallableStatement statement) throws Exception;		
	
	/**
	 * Executes JDBC call.
	 */
	public V execute(K args) throws Exception {
		Connection connection = null;
		CallableStatement statement = null;
		try {
			connection = getDataSource().getConnection();
			statement = connection.prepareCall(getQueryString());
			setParameters(statement, args);
			return doExecute(statement);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (statement!=null) {
					statement.close();
				}
				if (connection!=null) {
					connection.close();
				}
			} catch (Exception e) {}			
		}
	}
	
	protected String formatDate(Date date) {
		if (date==null) {
			return null;
		} else {
			return dateFormat.format(date);
		}
	}
	
	protected String formatDateTime(Date date) {
		if (date==null) {
			return null;
		} else {
			return dateTimeFormat.format(date);
		}
	}
	
	protected Date parseDate(String source) {
		if (source==null) {
			return null;
		} else {
			try {
				return dateFormat.parse(source);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Cannot parse date from [" + source + "]!");
			}
		}
	}
	
	protected Date parseDateTime(String source) {
		if (source==null) {
			return null;
		} else {
			try {
				return dateTimeFormat.parse(source);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Cannot parse date from [" + source + "]!");
			}
		}
	}
	
	//getters and setters
	protected DataSource getDataSource() {
		return dataSource;
	}
}
