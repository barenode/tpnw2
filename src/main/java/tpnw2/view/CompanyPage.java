package tpnw2.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Company;
import tpnw2.domain.CompanyCriteria;
import tpnw2.persistence.CompanyDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class CompanyPage extends PageBase {

	@SpringBean CompanyDao companyDao;
	
	public CompanyPage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Company, CompanyCriteria> main = new MasterDetail<Company, CompanyCriteria>("main", companyDao) {
			@Override
			protected Company newItem() {
				return new Company();
			}

			@Override
			protected Panel itemPanel(String id, IModel<Company> model) {
				return new CompanyPanel(id, model);
			}

			@Override
			protected List<? extends IColumn<Company, String>> getColumns() {
				List<IColumn<Company, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("name"), "name", "name"));
				columns.add(new PropertyColumn<>(new ResourceModel("phone"), "phone", "phone"));
				return columns;
			}

			@Override
			protected CompanyCriteria newCriteria() {
				return new CompanyCriteria();
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<CompanyCriteria> model) {
				return new CompanyCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "name";
			}

			@Override
			protected boolean checkBeforePersist(Company item) {
				//error("Duplicate city");
				return true;
			}			
		}; 
		add(main);		
	}
}
