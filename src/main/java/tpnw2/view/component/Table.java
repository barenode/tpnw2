package tpnw2.view.component;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.markup.repeater.data.IDataProvider;

@SuppressWarnings("serial")
public class Table<T, S> extends DataTable<T, S> {

	public Table(String id, List<? extends IColumn<T, S>> columns, IDataProvider<T> dataProvider, long rowsPerPage) {
		super(id, columns, dataProvider, rowsPerPage);
	}
}
