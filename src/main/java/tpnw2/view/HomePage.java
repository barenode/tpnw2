package tpnw2.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Office;
import tpnw2.persistence.OfficeDaoImpl;
import tpnw2.view.component.Table;

@SuppressWarnings("serial")
public class HomePage extends WebPage {

	@SpringBean OfficeDaoImpl officeDao;
	
	public HomePage(final PageParameters parameters) {
		super(parameters);
		List<IColumn<Office, String>> columns = new ArrayList<>();		
		columns.add(new PropertyColumn<>(Model.of("city"), "city", "city"));
		columns.add(new PropertyColumn<>(Model.of("city"), "city", "city"));
		Table<Office, String> table = new Table<>("table", columns, new OfficeDataProvider(), 10); 
		add(table);		
	}
	
	private class OfficeDataProvider extends SortableDataProvider<Office, String> {

		@Override
		public Iterator<? extends Office> iterator(long first, long count) {
			System.out.println(">> iterator " + first + "/" + count + "|" + getSortState());
			return officeDao.findAll().iterator();
		}

		@Override
		public long size() {
			return officeDao.count();
		}

		@Override
		public IModel<Office> model(Office object) {
			return Model.of(object);
		}		
	} 
}
