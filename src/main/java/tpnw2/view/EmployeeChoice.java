package tpnw2.view;

import java.util.Collections;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.Auth;
import tpnw2.domain.Employee;
import tpnw2.domain.EmployeeCriteria;
import tpnw2.persistence.EmployeeDao;

@SuppressWarnings("serial")
public class EmployeeChoice extends DropDownChoice<Employee> {

	@SpringBean EmployeeDao employeeDao;
	
	public EmployeeChoice(String id, IModel<Employee> model) {
		super(id, model, Collections.emptyList());
		EmployeeCriteria criteria;
		if (Auth.isAdministrator()) {
			criteria  = new EmployeeCriteria();
		} else {
			criteria  = new EmployeeCriteria(Auth.office());
		}
		setChoices(employeeDao.findAll(criteria));
		setChoiceRenderer(new EmployeeChoiceRenderer());
	}	
	 
	private static final class EmployeeChoiceRenderer extends ChoiceRenderer<Employee> {

		@Override
		public String getIdValue(Employee object, int index) {
			return String.valueOf(object.getId());
		}
	}
}
