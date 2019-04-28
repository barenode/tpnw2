package tpnw2.view;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.StringValidator;

import tpnw2.domain.Employee;

@SuppressWarnings("serial")
public class EmployeePanel extends Panel {

	public EmployeePanel(String id, IModel<Employee> model) {
		super(id);
		add(new TextField<String>("email", new PropertyModel<>(model, "email")).setRequired(true)
			.add(StringValidator.maximumLength(100))
			.add(EmailAddressValidator.getInstance()));
		add(new PasswordTextField("password", new PropertyModel<>(model, "password")).setRequired(true)
			.add(StringValidator.maximumLength(60)));
		add(new CheckBox("administrator", new PropertyModel<>(model, "administrator")));
		add(new TextField<String>("firstname", new PropertyModel<>(model, "firstname")).setRequired(true)
			.add(StringValidator.maximumLength(48)));
		add(new TextField<String>("lastname", new PropertyModel<>(model, "lastname")).setRequired(true)
			.add(StringValidator.maximumLength(48)));
		add(new OfficeChoice("office", new PropertyModel<>(model, "office")).setRequired(true));
		add(new CarMultipleChoice("cars", new PropertyModel<>(model, "cars")));
	}
}
