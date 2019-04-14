package tpnw2.view;

import java.util.Collections;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Company;
import tpnw2.domain.CompanyCriteria;
import tpnw2.persistence.CompanyDao;

@SuppressWarnings("serial")
public class CompanyChoice extends DropDownChoice<Company> {

	@SpringBean CompanyDao employeeDao;
	
	public CompanyChoice(String id, IModel<Company> model) {
		super(id, model, Collections.emptyList());
		setChoices(employeeDao.findAll(new CompanyCriteria()));
		setChoiceRenderer(new CompanyChoiceRenderer());
	}	
	 
	private static final class CompanyChoiceRenderer extends ChoiceRenderer<Company> {

		@Override
		public String getIdValue(Company object, int index) {
			return String.valueOf(object.getId());
		}
	}
}
