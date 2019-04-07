package tpnw2.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Office;
import tpnw2.domain.OfficeCriteria;
import tpnw2.persistence.OfficeDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class OfficePage extends PageBase {

	@SpringBean OfficeDao officeDao;
	
	public OfficePage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Office, OfficeCriteria> main = new MasterDetail<Office, OfficeCriteria>("main", officeDao) {
			@Override
			protected Office newItem() {
				return new Office();
			}

			@Override
			protected Panel itemPanel(String id, IModel<Office> model) {
				return new OfficePanel(id, model);
			}

			@Override
			protected List<? extends IColumn<Office, String>> getColumns() {
				List<IColumn<Office, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("city"), "city", "city"));
				return columns;
			}

			@Override
			protected OfficeCriteria newCriteria() {
				return new OfficeCriteria();
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<OfficeCriteria> model) {
				return new OfficeCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "city";
			}

			@Override
			protected boolean checkBeforePersist(Office item) {
				//error("Duplicate city");
				return true;
			}			
		}; 
		add(main);		
	}
}
