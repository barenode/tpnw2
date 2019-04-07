package tpnw2.persistence;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;

public interface Dao<T extends Serializable, C extends Serializable> {

	long count(C criteria);
	
	List<T> findAll(C criteria);
	
	List<T> page(long first, long count, C criteria, SortParam<String> sort);
	
	void remove(T item);
	
	void save(T item);
}
