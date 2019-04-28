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
import tpnw2.domain.Contract;
import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;
import tpnw2.domain.Office;
import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;
import tpnw2.persistence.CompanyDao;
import tpnw2.persistence.OrderDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class CompanyPage extends PageBase {

	@SpringBean CompanyDao companyDao;
	@SpringBean OrderDao orderDao;
	
	public CompanyPage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Company, CompanyCriteria> main = new MasterDetail<Company, CompanyCriteria>("main", companyDao) {
			@Override
			protected Company newItem() {
				Company item = new Company();
				item.setContract(new Contract());
				return item;
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
				List<Company> companies = companyDao.findAll(new CompanyCriteria(item.getName()));
				if (!companies.isEmpty()) {
					for (Company company : companies) {
						if (!company.getId().equals(item.getId())) {
							error(getString("error.client.name"));
							return false;
						}
					}
				}
				return true;
			}	
			
			@Override
			protected boolean checkBeforeRemove(Company item) { 
				List<Order> orders = orderDao.findAll(new OrderCriteria(item));
				if (!orders.isEmpty()) {
					error(getString("error.client.remove.order"));
					return false;
				} 
				return true; 				
			}	
		}; 
		add(main);		
	}
}
