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

import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;
import tpnw2.domain.Order;
import tpnw2.domain.OrderCriteria;
import tpnw2.persistence.EmployeeDao;
import tpnw2.persistence.OrderDao;
import tpnw2.view.component.MasterDetail;

@SuppressWarnings("serial")
public class EmployeePage extends PageBase {

	@SpringBean EmployeeDao employeeDao;
	@SpringBean OrderDao orderDao;
	
	public EmployeePage(final PageParameters parameters) {
		super(parameters);		
		MasterDetail<Employee, EmployeeCriteria> main = new MasterDetail<Employee, EmployeeCriteria>("main", employeeDao) {
			@Override
			protected Employee newItem() {
				return new Employee();
			}

			@Override
			protected Panel itemPanel(String id, IModel<Employee> model) {
				return new EmployeePanel(id, model);
			}

			@Override
			protected List<? extends IColumn<Employee, String>> getColumns() {
				List<IColumn<Employee, String>> columns = new ArrayList<>();		
				columns.add(new PropertyColumn<>(new ResourceModel("firstname"), "firstname", "firstname"));
				columns.add(new PropertyColumn<>(new ResourceModel("lastname"), "lastname", "lastname"));
				columns.add(new PropertyColumn<>(new ResourceModel("email"), "email", "email"));
				columns.add(new PropertyColumn<>(new ResourceModel("office"), "office", "office"));
				return columns;
			}

			@Override
			protected EmployeeCriteria newCriteria() {
				return new EmployeeCriteria();
			}

			@Override
			protected Panel criteriaPanel(String id, IModel<EmployeeCriteria> model) {
				return new EmployeeCriteriaPanel(id, model);
			}

			@Override
			protected String defaultSortProperty() {
				return "lastname";
			}

			@Override
			protected boolean checkBeforePersist(Employee item) {
				List<Employee> employees = employeeDao.findAll(new EmployeeCriteria(item.getEmail()));
				if (!employees.isEmpty()) {
					for (Employee employee : employees) {
						if (!employee.getId().equals(item.getId())) {
							error(getString("error.employee.email"));
							return false;
						}
					}
				}
				return true;
			}

			@Override
			protected boolean checkBeforeRemove(Employee item) {
				List<Order> orders = orderDao.findAll(new OrderCriteria(item));
				if (!orders.isEmpty()) {
					error(getString("error.employee.remove.order"));
					return false;
				} 
				return true;
			}			
		}; 
		add(main);		
	}

}
