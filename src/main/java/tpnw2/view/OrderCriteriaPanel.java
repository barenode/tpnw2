package tpnw2.view;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.Auth;
import tpnw2.domain.OrderCriteria;

@SuppressWarnings("serial")
public class OrderCriteriaPanel extends Panel {

	public OrderCriteriaPanel(String id, IModel<OrderCriteria> model) {
		super(id);
		add(new EmployeeChoice("employee", new PropertyModel<>(model, "employee")).setEnabled(Auth.isAdministrator()));
	}
}
