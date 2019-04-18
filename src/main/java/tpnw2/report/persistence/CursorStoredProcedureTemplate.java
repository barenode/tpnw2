package tpnw2.report.persistence;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public abstract class CursorStoredProcedureTemplate<V, K> extends StoredProcedureTemplate<List<V>, K> {
	
	public CursorStoredProcedureTemplate(DataSource dataSource) {
		super(dataSource);
	}
	
	protected abstract List<V> createResultList();
	
	protected abstract V buildResult(ResultSet resultSet) throws Exception;
	
	protected static Integer readInteger(ResultSet resultSet, String columnName) throws SQLException {
		final int intVal = resultSet.getInt(columnName);
		if ( resultSet.wasNull() ) {
			return null;
		} else {
			return Integer.valueOf(intVal);
		}
	}
	
	/**
	 * @see StoredProcedureTemplate#doExecute(CallableStatement)
	 */
	protected List<V> doExecute(CallableStatement statement) throws Exception {
		ResultSet result = null;
		try {
			int resultNum = 0;
			while(true) {
				boolean queryResult;
				int rowsAffected;
				if (1 == ++resultNum) {
					try {
						queryResult = statement.execute();
					} catch (SQLException e) {
						continue;
					}
				} else {
					try {           
						queryResult = statement.getMoreResults();
					} catch (SQLException e) {
	                        continue;
					}	
				}
				if (queryResult) {
					result = statement.getResultSet();
					break;
				} else {
					rowsAffected = statement.getUpdateCount();
					if (-1 == rowsAffected) {
						--resultNum;
						break;
					}
				}
			}
			List<V> resultList = createResultList();
			while (result.next()) {
				resultList.add(buildResult(result));
			} 		
			return resultList;
		} finally {
			try {
				if (result!=null) {
					result.close();
				}
			} catch (Exception e) {}			
		}
	}
}