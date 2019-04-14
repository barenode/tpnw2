package tpnw2.view;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import tpnw2.domain.Client;
import tpnw2.domain.Company;
import tpnw2.domain.Individual;
import tpnw2.domain.Order;
import tpnw2.domain.OrderStatus;
import tpnw2.view.component.TabsPanel;

@SuppressWarnings("serial")
public class OrderPanel extends Panel {

	public OrderPanel(String id, IModel<Order> model) {
		super(id);
		add(new TextField<Integer>("id", new PropertyModel<>(model, "id")).setEnabled(false));
		add(new TextField<OrderStatus>("status", new PropertyModel<>(model, "status")).setEnabled(false));
		add(new TextField<String>("departureAddress", new PropertyModel<>(model, "departureAddress")).setRequired(true));
		add(new DateTextField("departureDate", new PropertyModel<>(model, "departureDate"), "dd.MM.yyyy HH:mm").setRequired(true));
		add(new TextField<String>("destinationAddress", new PropertyModel<>(model, "destinationAddress")).setRequired(true));
		add(new EmployeeChoice("driver", new PropertyModel<>(model, "driver")).setRequired(true));
		
		final Client client = model.getObject().getClient();		
		List<ITab> tabs = Arrays.asList(
			new ITab() {
				@Override
				public IModel<String> getTitle() {
					return new ResourceModel("individual");
				}
				@Override
				public WebMarkupContainer getPanel(String containerId) {
					Individual individual = null;					
					if (client instanceof Individual) {
						individual = (Individual)client;
					} else {
						individual = new Individual();
					}							
					model.getObject().setClient(individual);
					return new OrderIndividual(containerId, Model.of(individual));
				}
				@Override
				public boolean isVisible() {
					return true;
				}				
			},
			new ITab() {
				@Override
				public IModel<String> getTitle() {
					return new ResourceModel("company");
				}
				@Override
				public WebMarkupContainer getPanel(String containerId) {
					if (client instanceof Individual) {
						model.getObject().setClient(null);
					}
					return new OrderCompany(containerId, new PropertyModel<>(model, "client"));
				}
				@Override
				public boolean isVisible() {
					return true;
				}				
			}			
		);
		add(new TabsPanel<>("client", tabs).setSelectedTab(client instanceof Individual?0:1).setEnabled(model.getObject().getId()==null));
	}
	
	private static class OrderCompany extends Panel {

		public OrderCompany(String id, IModel<Company> model) {
			super(id);
			add(new CompanyChoice("client", model));
		}		
	}
	
	private static class OrderIndividual extends Panel {

		public OrderIndividual(String id, IModel<Individual> model) {
			super(id);
			add(new TextField<String>("phone", new PropertyModel<>(model, "phone")).setRequired(true));
			add(new TextField<String>("firstname", new PropertyModel<>(model, "firstname")).setRequired(true));
			add(new TextField<String>("lastname", new PropertyModel<>(model, "lastname")).setRequired(true));
		}		
	}
}
