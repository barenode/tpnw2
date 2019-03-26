package tpnw2.view.component;

import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class Table<T, S> extends Panel {

	public Table(String id, List<? extends IColumn<T, S>> columns, ISortableDataProvider<T, S> dataProvider, long rowsPerPage) {
		super(id);
		DataTable<T, S> table = new DataTable<>("table", columns, dataProvider, rowsPerPage);
		table.addTopToolbar(new HeadersToolbar<>(table, dataProvider));
		add(table);
		add(new Pagination("pagination", table));
	}
	
	
	/**
	 * Generic column with single icon link.
	 */
	public static class LinkColumn<T, S> extends AbstractColumn<T, S> {

		private final String cssClass;
		
		public LinkColumn(String cssClass) {
			this(Model.of(""), cssClass);
		}
		
		public LinkColumn(IModel<String> displayModel, String cssClass) {
			super(displayModel);
			this.cssClass = cssClass;
		}

		@Override
		public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
			cellItem.add(new Link<T>(componentId) {
				@Override
				public void onClick() {
					System.out.println(rowModel);
				}				
			});
		}

		@Override
		public String getCssClass() {
			return cssClass;
		}	
	}
}