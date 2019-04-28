package tpnw2.view;

import java.util.Collections;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Office;
import tpnw2.domain.OfficeCriteria;
import tpnw2.persistence.OfficeDao;

@SuppressWarnings("serial")
public class OfficeChoice extends DropDownChoice<Office> {
	
	@SpringBean OfficeDao officeDao;
	
	public OfficeChoice(String id, IModel<Office> model) {
		super(id, model, Collections.emptyList());
		setChoices(officeDao.findAll(new OfficeCriteria()));
		setChoiceRenderer(new OfficeChoiceRenderer());
	}	
	 
	private static final class OfficeChoiceRenderer extends ChoiceRenderer<Office> {

		@Override
		public String getIdValue(Office object, int index) {
			return String.valueOf(object.getId());
		}
	}
}
