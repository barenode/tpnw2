package tpnw2.view;

import java.math.BigDecimal;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.StringValidator;

import tpnw2.Auth;
import tpnw2.domain.Company;

@AuthorizeInstantiation(Auth.SIGNEDIN)
@SuppressWarnings("serial")
public class CompanyPanel extends Panel {

	public CompanyPanel(String id, IModel<Company> model) {
		super(id);
		add(new TextField<String>("name", new PropertyModel<>(model, "name")).setRequired(true)
			.add(StringValidator.maximumLength(50)));
		add(new TextField<String>("phone", new PropertyModel<>(model, "phone")).setRequired(true)
			.add(StringValidator.maximumLength(16)));
		add(new TextField<BigDecimal>("pricePerKm", new PropertyModel<>(model, "contract.pricePerKm")).setRequired(true));
	}
}
