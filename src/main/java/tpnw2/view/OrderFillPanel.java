package tpnw2.view;

import java.math.BigDecimal;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import tpnw2.domain.Individual;
import tpnw2.domain.Order;

@SuppressWarnings("serial")
public abstract class OrderFillPanel extends Panel {

	public OrderFillPanel(String id, IModel<Order> model) {
		super(id);
		Form<Order> form = new Form<>("form");
		add(form);
		form.add(new TextField<>("distance", new PropertyModel<>(model, "distance")).setRequired(true));
		WebMarkupContainer priceEnvelope = new WebMarkupContainer("priceEnvelope") {
			@Override
			public boolean isVisible() {
				return isIndividual(model);
			}
		};
		form.add(priceEnvelope);
		priceEnvelope.add(new TextField<BigDecimal>("price", new PropertyModel<>(model, "price")) {
			@Override
			public boolean isEnabled() {
				return isIndividual(model);
			}

			@Override
			public boolean isRequired() {
				return isIndividual(model);
			}
			
			@Override
			public boolean isVisible() {
				return isIndividual(model);
			}
		});
		form.add(new CarChoice("car", new PropertyModel<>(model, "car"), model.getObject().getDriver().getCars()).setRequired(true));
		form.add(new SubmitLink("fill") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				OrderFillPanel.this.onSubmit(model);					
			}						
		});	
		form.add(new Link<Page>("cancel") {
			@Override
			public void onClick() {
				OrderFillPanel.this.onCancel();
			}			
		});	
		
	}
	
	private boolean isIndividual(IModel<Order> model) {
		return model.getObject().getClient() instanceof Individual;
	}	
	
	protected abstract void onSubmit(IModel<Order> model);
	
	protected abstract void onCancel();
}
